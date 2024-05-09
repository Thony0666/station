package com.anthonyo.station.rasta.connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void getConnection() {
        DatabaseSettings settings = new DatabaseSettings();
        settings.setUrl("jdbc:postgresql://localhost:5432/postgres");
        settings.setUsername("postgres");
        settings.setPassword("zazabe02");
        Config c = new Config(settings);
        assertNotNull(c.getConnection());
    }
}