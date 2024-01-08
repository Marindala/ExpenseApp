package main.dao.impl;

import main.config.JdbcConfiguration;
import main.dao.CategoryDao;
import main.dao.dto.CategoryDto;
import main.entities.Category;
import main.exceptions.DAOException;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final String GET_ALL_CATEGORY = "SELECT * FROM category";
    private static final String INSERT_INTO_CATEGORY = "INSERT INTO category (name, Id) VALUES (?, ?)";
    private static final String UPDATE_CATEGORY = "UPDATE category SET name = ?, operationTypeIdId = ? WHERE id = ?";
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name = ?";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
    private static final String DELETE_CATEGORY = "DELETE  FROM category WHERE id = ?";
    private final Connection connection;
    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public CategoryDto getById(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCategoryDto(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error retrieving category by ID", e);
        }
    }

    @Override
    public Category getByName(String name) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("tipoOperacionId")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error retrieving category by Name", e);
        }
    }

    @Override
    public List<CategoryDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORY)) {
            ResultSet resultSet = statement.executeQuery();
            List<CategoryDto> categoryDto = new ArrayList<>();
            // Iterate through the ResultSet to add the category to the list
            // and meanwhile, perform mapping for each item
            while (resultSet.next()) {
                categoryDto.add(mapDtoToCategoryDto(resultSet));
            }
            return categoryDto;
        } catch (SQLException e) {
            throw new DAOException("Error retrieving the list of category", e);
        }
    }

    @Override
    public void insert(CategoryDto categoryDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CATEGORY)) {
            // Mapping from dto to entity
            Category category = mapDtoToCategory(categoryDto);

            statement.setString(1, category.getName());
            statement.setInt(2, category.getOperationTypeId());
            int affectedRows = statement.executeUpdate();
            // Validate if the execution result of the statement does not return affected rows,
            // then there was an error inserting into the database
            if (affectedRows == 0) {
                throw new DAOException("Error inserting the Category, no rows were affected.");
            }
        } catch (DAOException | SQLException e) {
            assert e instanceof SQLException;
            throw new DAOException("Error inserting the Category", (SQLException) e);
        }
    }

    @Override
    public void update(CategoryDto categoryDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {

            Category category = mapDtoToCategory(categoryDto);

            statement.setString(1, category.getName());
            statement.setInt(2, category.getOperationTypeId());
            statement.setInt(3, category.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error updating the Category, no rows were affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating the Category", e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Error deleting the Category, no rows were affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting the Category", e);
        }
    }

    private Category mapDtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setId(categoryDto.getId());
        category.setOperationTypeId(categoryDto.getOperationId());
        return category;
    }

    private CategoryDto mapDtoToCategoryDto(ResultSet resultSet) throws SQLException {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(resultSet.getInt("id"));
        categoryDto.setName(resultSet.getString("name"));
        categoryDto.setOperationId(resultSet.getInt("operationtypeId"));
        return categoryDto;
    }

    private CategoryDto mapResultSetToCategoryDto(ResultSet resultSet) throws SQLException {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(resultSet.getInt("id"));
        categoryDto.setName(resultSet.getString("name"));
        categoryDto.setOperationId(resultSet.getInt("tipoOperacionId"));
        return categoryDto;
    }

}
