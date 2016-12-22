package nl.zoostation.database.app.config;

import nl.zoostation.database.aspect.MethodParameterValidationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author valentinnastasi
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {

    @Bean
    public MethodParameterValidationAspect methodParameterValidationAspect() {
        return new MethodParameterValidationAspect();
    }

}
