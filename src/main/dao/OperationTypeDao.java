package main.dao;

import main.dao.dto.OperationTypeDto;
import main.exceptions.DAOException;

import java.util.List;

public interface OperationTypeDao {
    List<OperationTypeDto> getAll() throws DAOException;
}
