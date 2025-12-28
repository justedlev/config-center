package io.justedlev.msrvs.configcenter.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ConfigurationProperties(ConfigCenterConfigurationProperties.PREFIX)
public class ConfigCenterConfigurationProperties {
    public static final String PREFIX = "configcenter";
    private boolean enabled = true;
    @NestedConfigurationProperty
    private ConfigCenterConfigurationProperties security;
}
