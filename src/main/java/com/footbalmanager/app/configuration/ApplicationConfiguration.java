package com.footbalmanager.app.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.footbalmanager.app.repository")
@ComponentScan(basePackages = {"com.footbalmanager.app.controller", "com.footbalmanager.app.services"})
@EntityScan(basePackages = "com.footbalmanager.app.domain")
@EnableTransactionManagement
public class ApplicationConfiguration {
}
