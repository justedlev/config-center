package io.github.justedlev.configcenter.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.cloud.config.server.config.EnvironmentRepositoryConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@AutoConfiguration(before = EnvironmentRepositoryConfiguration.class)
@Profile("jdbc")
@ConditionalOnBooleanProperty(prefix = "spring.cloud.config.server.jdbc", value = "enabled")
@Import({DataSourceAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
public class ConfigCenterJdbcTemplateAutoConfiguration {
}
