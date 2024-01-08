package main.dao.impl;

import main.dao.TransactionsDao;
import main.dao.dto.TransactionsDto;
import main.entities.Category;
import main.entities.Transactions;
import main.exceptions.DAOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDaoImpl implements TransactionsDao {
    private static final String GET_ALL_TRANSACTIONS = "SELECT * FROM transactions";

    private static final String INSERT_INTO_TRANSACTIONS = "INSERT INTO transactions (id, accountManagerId, categoryId, date, amount) VALUES (25, ?, ?, ?, ?)";
    private static final String GET_TRANSACTIONS_BY_ID = "SELECT * FROM transactions WHERE id = ?";
    private final Connection connection;

    public TransactionsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransactionsDto getById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<TransactionsDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TRANSACTIONS)) {
            ResultSet resultSet = statement.executeQuery();
            List<TransactionsDto> transactionsDto = new ArrayList<>();

            while (resultSet.next()) {
                transactionsDto.add(mapDtoTotransactionsDto(resultSet));
            }
            return transactionsDto;
        } catch (SQLException e) {
            throw new DAOException("Error getting list of Transactions", e);
        }
    }

    @Override
    public void insert(TransactionsDto transactionsDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TRANSACTIONS)) {

            //Transactions transactions = mapDtoToTransactions(transactionsDto);
            Transactions transactions = mapDtoToTransactions(transactionsDto);

            statement.setLong(1, transactions.getAccountManagerId());
            statement.setLong(2, transactions.getCategoryId());
            statement.setString(3, transactions.getDate());
            statement.setDouble(4, transactions.getAmount());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Error inserting the Transaction");
            }
        } catch (DAOException | SQLException e) {
            assert e instanceof SQLException;
            throw new DAOException("Error inserting Transaction", (SQLException) e);
        }
    }

    @Override
    public void update(TransactionsDto transactionsDto) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }

    private Transactions mapDtoToTransactions(TransactionsDto transactionsDto) {
        Transactions transactions = new Transactions();
        transactions.setAccountManagerId(transactionsDto.getAccountManagerId());
        transactions.setCategoryId(transactionsDto.getCategoryId());
        transactions.setDate(transactionsDto.getDate());
        transactions.setAmount(transactionsDto.getAmount());
        return transactions;
    }

    private TransactionsDto mapDtoTotransactionsDto(ResultSet resultSet) throws SQLException {
        TransactionsDto transactionsDto = new TransactionsDto();
        transactionsDto.setAccountManagerId(resultSet.getInt("accounManagerId"));
        transactionsDto.setCategoryId(resultSet.getInt("categoryId"));
        //transaccionesDto.setFecha(String.valueOf(resultSet.getDate("fecha")));
        transactionsDto.setDate(resultSet.getString("date"));
        transactionsDto.setAmount(resultSet.getDouble("amount"));
        return transactionsDto;
    }
}
