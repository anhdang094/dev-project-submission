package dev.remitano.core.configuration.application;

import dev.remitano.infrastructure.configuration.AccessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {"classpath:${appenv}.config.properties"})
@ComponentScan(basePackages = "art.spectacle.infrastructure")
public class ApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    AccessInfo getAccessInfo() {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setServletPath(env.getProperty("server.servlet.path"));
        accessInfo.setApiKeys(env.getProperty("access.info.api-keys"));
        accessInfo.setEnv(env.getProperty("app.enviroment"));
        return accessInfo;
    }

}
