package com.anthonyo.station.rasta.repository.impl;

import com.anthonyo.station.rasta.entities.Product;
import com.anthonyo.station.rasta.entities.Station;
import com.anthonyo.station.rasta.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Optional;

@Service
public class ProductRepositoryImpl implements ProductRepository {
    private final Connection connection;

    public ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Product createProduct(Product toCreate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name,price,quantity) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, toCreate.getName());
            preparedStatement.setDouble(2, toCreate.getPrice());
            preparedStatement.setDouble(3, toCreate.getQuantity());
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
    public Product updateProductById(Product toUpdateById) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name=?,price=?,quantity=? WHERE id =?");
            preparedStatement.setString(1, toUpdateById.getName());
            preparedStatement.setDouble(2, toUpdateById.getPrice());
            preparedStatement.setDouble(3, toUpdateById.getQuantity());
            preparedStatement.setInt(4, toUpdateById.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM product  WHERE id=?");
            preparedStatement.setInt(1,id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        Product.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .price(resultSet.getDouble("price"))
                                .quantity(resultSet.getDouble("quantity"))
                                .build()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
