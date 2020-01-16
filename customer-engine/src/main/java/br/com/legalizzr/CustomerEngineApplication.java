package br.com.legalizzr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = "br.com.commons.customer")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomerEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerEngineApplication.class, args);
    }

}