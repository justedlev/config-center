package io.justedlev.msrvs.configcenter.configuration;

import io.justedlev.msrvs.configcenter.configuration.properties.ConfigCenterConfigurationProperties;
import io.justedlev.msrvs.configcenter.configuration.properties.ConfigCenterSecurityConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.ConditionalOnOAuth2ClientRegistrationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConditionalOnOAuth2ClientRegistrationProperties
@EnableConfigurationProperties({
        ConfigCenterConfigurationProperties.class,
        ConfigCenterSecurityConfigurationProperties.class,
})
@RequiredArgsConstructor
public class ConfigCenterSecurityConfiguration {
    private final ConfigCenterSecurityConfigurationProperties properties;

    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(customizer ->
                        customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests(spec -> {
                    properties.getWhitelist().forEach((k, v) -> spec.requestMatchers(k, v).permitAll());
                    spec.anyRequest().authenticated();
                })
                .build();
    }
}
