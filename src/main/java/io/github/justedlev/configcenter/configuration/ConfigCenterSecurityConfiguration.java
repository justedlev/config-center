package io.github.justedlev.configcenter.configuration;

import io.github.justedlev.configcenter.autoconfigure.ConfigCenterOAuth2ResourceServerAutoConfiguration;
import io.github.justedlev.configcenter.configuration.properties.ConfigCenterConfigurationProperties;
import io.github.justedlev.configcenter.configuration.properties.ConfigCenterSecurityConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConditionalOnBean(ConfigCenterOAuth2ResourceServerAutoConfiguration.class)
@EnableConfigurationProperties({
        ConfigCenterConfigurationProperties.class,
        ConfigCenterSecurityConfigurationProperties.class,
})
@RequiredArgsConstructor
public class ConfigCenterSecurityConfiguration {
    private final ConfigCenterSecurityConfigurationProperties properties;

    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity httpSecurity) {
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
