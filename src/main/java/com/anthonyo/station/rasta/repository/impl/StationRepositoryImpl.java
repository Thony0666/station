package com.anthonyo.station.rasta.repository.impl;

import com.anthonyo.station.rasta.entities.Station;
import com.anthonyo.station.rasta.repository.StationRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StationRepositoryImpl implements StationRepository {
    private final Connection connection;

    public StationRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Station createStation(Station toCreate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO station (name,place,max_volume_gasoline,max_volume_diesel,max_volume_petrol) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, toCreate.getName());
            preparedStatement.setString(2, toCreate.getPlace());
            preparedStatement.setDouble(3, toCreate.getMaxVolumeGasoline());
            preparedStatement.setDouble(4, toCreate.getMaxVolumeDiesel());
            preparedStatement.setDouble(5, toCreate.getMaxVolumePetrol());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    toCreate.setId(id);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toCreate;

    }

    @Override
    public Station updateStation(Station toUpdate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE station SET name=?,place=?,max_volume_gasoline=?,max_volume_diesel=?,max_volume_petrol=? WHERE id =?");
            preparedStatement.setString(1, toUpdate.getName());
            preparedStatement.setString(2, toUpdate.getPlace());
            preparedStatement.setDouble(3, toUpdate.getMaxVolumeGasoline());
            preparedStatement.setDouble(4, toUpdate.getMaxVolumeDiesel());
            preparedStatement.setDouble(5, toUpdate.getMaxVolumePetrol());
            preparedStatement.setInt(6,toUpdate.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toUpdate;
    }

    @Override
    public List<Station> findAllStation() {
        var stations=new ArrayList<Station>();
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM station");
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stations.add(
                        Station.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .place(resultSet.getString("place"))
                                .maxVolumeGasoline(resultSet.getDouble("max_volume_gasoline"))
                                .maxVolumeDiesel(resultSet.getDouble("max_volume_diesel"))
                                .maxVolumePetrol(resultSet.getDouble("max_volume_petrol"))
                                .build()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stations;
    }

    @Override
    public Optional<Station> findById(Integer id) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM station WHERE id=?");
            preparedStatement.setInt(1,id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        Station.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .place(resultSet.getString("place"))
                                .maxVolumeGasoline(resultSet.getDouble("max_volume_gasoline"))
                                .maxVolumeDiesel(resultSet.getDouble("max_volume_diesel"))
                                .maxVolumePetrol(resultSet.getDouble("max_volume_petrol"))
                                .build()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
