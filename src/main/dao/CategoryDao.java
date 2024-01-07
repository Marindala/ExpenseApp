package main.dao;

import main.dao.dto.CategoryDto;
import main.entities.Category;
import main.exceptions.DAOException;

import java.util.List;


public interface CategoryDao {
    CategoryDto getById(int id) throws DAOException;
    Category getByName(String name) throws DAOException;
    List<CategoryDto> getAll() throws DAOException;
    void insert(CategoryDto categoryDto) throws DAOException;
    void update(CategoryDto categoryDto) throws DAOException;
    void delete(int id) throws DAOException;
}
