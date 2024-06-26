package hunnid.com.blog.config;

import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.core.convert.converter.Converter;
import java.util.Collections;
import java.util.Map;

public class OrganizationSubClaimAdapter implements
        Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter delegate =
            MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);
        String organization = convertedClaims.get("organization") != null ?
                (String) convertedClaims.get("organization") : "unknown";

        convertedClaims.put("organization", organization.toUpperCase());

        return convertedClaims;
    }


}


