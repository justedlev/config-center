package io.justedlev.msrvs.configcenter.autoconfiguration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnBooleanProperty(prefix = "spring.rabbitmq", name = "enabled", matchIfMissing = true)
@Import(RabbitAutoConfiguration.class)
public class ConfigCenterRabbitMQAutoConfiguration {
    @Getter
    @Setter(onMethod_ = @Value("${spring.rabbitmq.enabled:true}"))
    private boolean enabled;
}
