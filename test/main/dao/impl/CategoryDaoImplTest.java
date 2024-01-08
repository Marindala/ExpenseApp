package main.dao.impl;

import main.dao.CategoryDao;
import main.dao.dto.CategoryDto;
import main.entities.Category;
import main.exceptions.DAOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryDaoImplTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private CategoryDao categoryDao;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        mockConnection.createStatement();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        categoryDao = new CategoryDaoImpl(mockConnection);
    }

    @Test
    void insert_WhenValid() throws SQLException, DAOException {
        // GIVEN
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Incomes");
        categoryDto.setOperationId(1);

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        categoryDao.insert(categoryDto);

        // THEN
        verify(mockPreparedStatement).setString(1, categoryDto.getName());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getCategoryByName_WhenValidName() throws SQLException, DAOException {
        // GIVEN
        String name = "Salary";
        int operation = 1;
        int categoryId = 1;

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(categoryId);
        when(mockResultSet.getString("name")).thenReturn(name);

        // WHEN
        Category resultCategory = categoryDao.getByName(name);

        // THEN
        verify(mockPreparedStatement).setString(1, name);
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet).next();
        verify(mockResultSet).getInt("id");
        verify(mockResultSet).getString("name");

        // Assertions
        assert resultCategory != null;
        assert resultCategory.getId() == categoryId;
        assert resultCategory.getName().equals(name);
    }

}