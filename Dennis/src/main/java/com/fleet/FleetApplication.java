package com.fleet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FleetApplication {
    public static void main(String[] args) {
        SpringApplication.run(FleetApplication.class, args);
    }

    @Bean
    public DataSource dataSource(@Autowired Environment env) {

        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/fleetdb")
                .username("fleetuser")
                .password("fleetpass")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}