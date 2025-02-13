package com.ifortex.bookservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

/**
 * Конфигурационный файл для поднятия одного контейнера перед тестами.
 */
@Slf4j
@ActiveProfiles("test")
@ContextConfiguration(initializers = { TestContainersConfig.Initializer.class })
@Testcontainers
public abstract class TestContainersConfig {
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("book_service")
            .withUsername("book_service")
            .withPassword("book_service").withInitScript("test_data.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.flyway.enabled=true", "spring.flyway.schemas=book_service")
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}