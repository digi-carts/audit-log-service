package com.digicart.auditlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring Boot entry point for the <em>audit-log-service</em> microservice.
 */
@SpringBootApplication
@EnableJpaAuditing
public class AuditLogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuditLogServiceApplication.class, args);
    }
}
