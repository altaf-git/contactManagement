package org.contacts.management.web.manage.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class})
@ComponentScan(basePackages = "org.contacts.management")
@Configuration
@EnableWebFlux
@EnableWebFluxSecurity
@ConfigurationPropertiesScan(basePackages = {"org.contacts.management"})
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }
}
