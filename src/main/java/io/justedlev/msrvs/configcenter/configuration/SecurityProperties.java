package io.justedlev.msrvs.configcenter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * Configuration properties for managing security settings in the application.
 * This class allows configuration of various security-related properties, such as whitelisted endpoints,
 * token expiration, and other security settings.
 * <p>
 * The configuration is bound to the properties prefixed with "security".
 * <p>
 * Example configuration in application.yml:
 *
 * <pre class="code">
 * security:
 *   whitelist:
 *     GET: ["/public", "/api/health"]
 * </pre>
 *
 * @see ConfigurationProperties
 */
@Getter
@Setter
@ConfigurationProperties(SecurityProperties.PREFIX)
public class SecurityProperties {
    public static final String PREFIX = "config-center.security";
    /**
     * A map that holds the HTTP method as the key and an array of allowed endpoint patterns as the value.
     * <p>
     * For example:
     *
     * <pre class="code">
     * whitelist:
     *   GET: ["/public", "/api/health"]
     *   POST: ["/api/login"]
     * </pre>
     *
     * @see HttpMethod
     */
    private Map<HttpMethod, String[]> whitelist = Map.of();
}
