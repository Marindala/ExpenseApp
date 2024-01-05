package main.dao.impl;

import main.dao.AccountManagerDao;
import main.dao.dto.AccountManagerDto;
import exceptions.DAOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerImpl implements AccountManagerDao {

    private static final String GET_ALL_ACCOUNT_MANAGERS = "SELECT * FROM accountmanager";
    private static final String GET_ACCOUNT_MANAGER_BY_ID = "SELECT * FROM accountmanager WHERE id = ?";
    private final Connection connection;

    public AccountManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<AccountManagerDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ACCOUNT_MANAGERS)) {
            ResultSet resultSet = statement.executeQuery();
            List<AccountManagerDto> accountManagerDtos = new ArrayList<>();
            // Iterate over the ResultSet to add the account manager to the list
            // and while adding, perform mapping for each item
            while (resultSet.next()) {
                accountManagerDtos.add(mapDtoToAccountManagerDto(resultSet));
            }
            return accountManagerDtos;
        } catch (SQLException e) {
            throw new DAOException("Error getting the list of Account Managers", e);
        }
    }

    @Override
    public AccountManagerDto getById(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_MANAGER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAccountManagerDto(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error getting the account manager by ID", e);
        }
    }

    private AccountManagerDto mapDtoToAccountManagerDto(ResultSet resultSet) throws SQLException {
        AccountManagerDto accountManagerDto = new AccountManagerDto();
        accountManagerDto.setId(resultSet.getInt("id"));
        accountManagerDto.setAccountTypeId(resultSet.getInt("accountTypeId"));
        accountManagerDto.setDescription(resultSet.getString("description"));
        accountManagerDto.setInitialBalance(resultSet.getDouble("initialBalance"));
        return accountManagerDto;
    }

    private AccountManagerDto mapResultSetToAccountManagerDto(ResultSet resultSet) throws SQLException {
        AccountManagerDto accountManagerDto = new AccountManagerDto();
        accountManagerDto.setId(resultSet.getInt("id"));
        accountManagerDto.setAccountTypeId(resultSet.getInt("accountTypeId"));
        accountManagerDto.setDescription(resultSet.getString("description"));
        accountManagerDto.setInitialBalance(resultSet.getDouble("initialBalance"));
        return accountManagerDto;
    }
}