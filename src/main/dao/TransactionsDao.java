package main.dao;

import main.dao.dto.TransactionsDto;
import main.exceptions.DAOException;

import java.util.List;

public interface TransactionsDao {
    TransactionsDto getById(int id) throws DAOException;
    List<TransactionsDto> getAll() throws DAOException;
    void insert(TransactionsDto transactionsDto) throws DAOException;
    void update(TransactionsDto transactionsDto) throws DAOException;
    void delete(int id) throws DAOException;
}
