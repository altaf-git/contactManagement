package org.contacts.management.web.manage.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
public class ServerPortRelatedConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Value("${management.endpoint.port:18082}")
    int port;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        log.info("Setting the server port as " + port);
        factory.setPort(port);
    }
}
