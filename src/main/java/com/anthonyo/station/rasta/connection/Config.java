package com.anthonyo.station.rasta.connection;

import com.anthonyo.station.rasta.repository.StationRepository;
import com.anthonyo.station.rasta.repository.impl.StationRepositoryImpl;
import com.anthonyo.station.rasta.services.StationService;
import com.anthonyo.station.rasta.services.impl.StationServiceImpl;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Configuration
public class Config {
    private final DatabaseSettings databaseSettings;

    public Config(
            DatabaseSettings databaseSettings
    ) {
        this.databaseSettings = databaseSettings;
    }

    @Bean
    public Connection getConnection()  {
        try {
        return DriverManager.getConnection(databaseSettings.getUrl(), databaseSettings.getUsername(), databaseSettings.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public StationRepository stationRepository() {
        return  new StationRepositoryImpl(getConnection());
    }

    @Bean
    public StationService stationService () {
        return new StationServiceImpl(stationRepository());
    }
    @Bean
    public StationService stationService2 () {
        return new StationServiceImpl(stationRepository());
    }

}

