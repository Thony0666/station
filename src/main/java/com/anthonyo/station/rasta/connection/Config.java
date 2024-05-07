package com.anthonyo.station.rasta.connection;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Configuration
public class Config {
    @Value("${url.database.postgres}")
    private String url;
    @Value("${username.database.postgres}")
    private String username;
    @Value("${password.database.postgres}")
    private String password;

    @Bean
    public Connection getConnection()  {
        try {
        return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
