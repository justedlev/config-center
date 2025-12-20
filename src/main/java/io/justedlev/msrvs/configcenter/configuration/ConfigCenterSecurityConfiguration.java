package io.justedlev.msrvs.configcenter.configuration;

import io.justedlev.msrvs.configcenter.configuration.properties.ConfigCenterConfigurationProperties;
import io.justedlev.msrvs.configcenter.configuration.properties.ConfigCenterSecurityConfigurationProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConditionalOnBooleanProperty(prefix = "spring.security.oauth2", value = "enabled")
@EnableConfigurationProperties({
        ConfigCenterConfigurationProperties.class,
        ConfigCenterSecurityConfigurationProperties.class,
})
@RequiredArgsConstructor
public class ConfigCenterSecurityConfiguration {
    private final ConfigCenterSecurityConfigurationProperties properties;

    @Getter
    @Setter(onMethod_ = @Value("${spring.security.oauth2.enabled:true}"))
    private boolean enabled;


    @Bean
    @ConditionalOnMissingBean
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
