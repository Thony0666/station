package com.anthonyo.station.rasta.repository.impl;

import com.anthonyo.station.rasta.dtos.Facture;
import com.anthonyo.station.rasta.entities.Operation;
import com.anthonyo.station.rasta.entities.Station;
import com.anthonyo.station.rasta.repository.OperationRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OperationRepositoryImpl implements OperationRepository {
    private final Connection connection;

    public OperationRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Operation createTransaction(Operation toCreate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO operation (id_station,id_product,type,quantity,amounts,date_operation) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, toCreate.getIdStation());
            preparedStatement.setInt(2, toCreate.getIdProduct());
            preparedStatement.setString(3, toCreate.getType());
            preparedStatement.setDouble(4, toCreate.getQuantity());
            preparedStatement.setDouble(5, toCreate.getAmounts());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(toCreate.getDateOperation()));
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

        return toCreate ;
    }

    @Override
    public Optional<Station> findByIdStation(Integer id) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM sta WHERE id=?");
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

    @Override
    public Optional<Station> findByIdProduct(Integer id) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM  WHERE id=?");
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

    @Override
    public List<Facture> getAllOperations () {
        var factures=new ArrayList<Facture>();
        try {
            var preparedStatement = connection.prepareStatement("SELECT p.name , o.date_operation , o.type , o.amounts FROM product AS p INNER JOIN operation AS o ON o.id_product=p.id");
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                factures.add(
                        new Facture(resultSet.getTimestamp("date_operation").toLocalDateTime(),resultSet.getString("name"),resultSet.getString("type"),resultSet.getDouble("amounts"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return factures;
    }
}
