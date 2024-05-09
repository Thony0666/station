package com.anthonyo.station.rasta.connection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database.postgres")
@Getter
@Setter
public class DatabaseSettings {
    private String url;
    private String username;
    private String password;
}
