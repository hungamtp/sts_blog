package hunnid.com.blog.config;

import com.amazonaws.services.lightsail.model.UnauthenticatedException;
import hunnid.com.blog.entity.User;
import hunnid.com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public AbstractAuthenticationToken convert(Jwt jwt) {
        userRepository.findByEmail(jwt.getClaimAsString("email")).orElseThrow(
                () -> new UnauthenticatedException("not allowed")
        );
        return new JwtAuthenticationToken(jwt, new ArrayList<>());
    }
}
