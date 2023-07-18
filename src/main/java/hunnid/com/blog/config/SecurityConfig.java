package hunnid.com.blog.config;

import hunnid.com.blog.service.UserService;
import hunnid.com.blog.service.impl.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oauthUserService;
    private final UserService userService;
    private final CustomAuthenticationConverter converter;

    @Value("${fe.url}")
    private String feUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("POST", "PATCH", "/post").authenticated()
                .antMatchers("/manage").authenticated()
                .antMatchers("/tags").permitAll()
                .antMatchers("/languages").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                        var user = (DefaultOidcUser) authentication.getPrincipal();

                        if (userService.processOAuthPostLogin((user).getAttribute("email"))) {
                            response.sendRedirect(feUrl + "admin?token=" + user.getIdToken().getTokenValue());
                        } else
                            response.sendRedirect(feUrl);

                    }
                })
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(converter);
    }


    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();

        jwtDecoder.setClaimSetConverter(new OrganizationSubClaimAdapter());
        return jwtDecoder;
    }
}
