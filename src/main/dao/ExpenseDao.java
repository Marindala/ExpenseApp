package main.dao;

public interface ExpenseDao {
    List<ExpenseDto> getAll() throws DAOException;
}
