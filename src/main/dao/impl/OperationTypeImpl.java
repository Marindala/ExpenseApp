package main.dao.impl;

import main.dao.OperationTypeDao;
import main.dao.dto.OperationTypeDto;
import main.exceptions.DAOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationTypeImpl implements OperationTypeDao {
    private static final String GET_ALL_OPERATIONTYPE = "SELECT * FROM operationtype";
    private final Connection connection;

    public OperationTypeImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OperationTypeDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_OPERATIONTYPE)) {
            ResultSet resultSet = statement.executeQuery();
            List<OperationTypeDto> OperationTypeDto = new ArrayList<>();

            while (resultSet.next()) {
                OperationTypeDto.add(mapDtoToOperationTypeDto(resultSet));
            }
            return OperationTypeDto;
        } catch (SQLException e) {
            throw new DAOException("Error getting list of Type of Operations", e);
        }
    }



    private OperationTypeDto mapDtoToOperationTypeDto(ResultSet resultSet) throws SQLException {
        OperationTypeDto operationTypeDto = new OperationTypeDto();
        OperationTypeDto.setId(resultSet.getInt("id"));
        OperationTypeDto.setName(resultSet.getString("name"));
        OperationTypeDto.setOperation(resultSet.getString("operation"));
        return operationTypeDto;
    }
}
