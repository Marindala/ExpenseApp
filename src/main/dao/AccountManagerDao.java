package main.dao;

import main.dao.dto.AccountManagerDto;
import main.exceptions.DAOException;

import java.util.List;

public interface AccountManagerDao {
    List<AccountManagerDto> getAll() throws DAOException;

    AccountManagerDto getById(int id) throws DAOException;
}
