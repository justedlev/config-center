package io.justedlev.msrvs.configcenter.autoconfigure;

import io.justedlev.msrvs.configcenter.configuration.properties.ConfigCenterSecurityConfigurationProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ExpressionJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;

@AutoConfiguration
@ConditionalOnBooleanProperty(prefix = "spring.security.oauth2.resourceserver", name = "enabled", matchIfMissing = true)
@Import(OAuth2ResourceServerAutoConfiguration.class)
public class ConfigCenterOAuth2ResourceServerAutoConfiguration {
    private static final PropertyMapper PROPERTY_MAPPER = PropertyMapper.get().alwaysApplyingWhenNonNull();
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    @Getter
    @Setter(onMethod_ = @Value("${spring.security.oauth2.resourceserver.enabled:true}"))
    private boolean enabled;

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(
            OAuth2ResourceServerProperties properties,
            Collection<Converter<Jwt, Collection<GrantedAuthority>>> converters
    ) {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        PROPERTY_MAPPER.from(properties.getJwt()::getPrincipalClaimName).to(jwtAuthenticationConverter::setPrincipalClaimName);
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new DelegatingJwtGrantedAuthoritiesConverter(converters));

        return jwtAuthenticationConverter;
    }

    @Bean
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter(OAuth2ResourceServerProperties properties) {
        var converter = new JwtGrantedAuthoritiesConverter();
        PROPERTY_MAPPER.from(properties.getJwt()::getAuthorityPrefix).to(converter::setAuthorityPrefix);
        PROPERTY_MAPPER.from(properties.getJwt()::getAuthoritiesClaimDelimiter).to(converter::setAuthoritiesClaimDelimiter);
        PROPERTY_MAPPER.from(properties.getJwt()::getAuthoritiesClaimName).to(converter::setAuthoritiesClaimName);

        return converter;
    }

    @Bean
    ExpressionJwtGrantedAuthoritiesConverter resourceAccessRolesJwtGrantedAuthoritiesConverter() {
        var exp = PARSER.parseExpression("[resource_access][[azp][0]][roles]");
        var converter = new ExpressionJwtGrantedAuthoritiesConverter(exp);
        converter.setAuthorityPrefix(ConfigCenterSecurityConfigurationProperties.ROLE_PREFIX);

        return converter;
    }

    @Bean
    ExpressionJwtGrantedAuthoritiesConverter realmAccessRolesJwtGrantedAuthoritiesConverter() {
        var exp = PARSER.parseExpression("[realm_access][roles]");
        var converter = new ExpressionJwtGrantedAuthoritiesConverter(exp);
        converter.setAuthorityPrefix(ConfigCenterSecurityConfigurationProperties.ROLE_PREFIX);

        return converter;
    }
}
