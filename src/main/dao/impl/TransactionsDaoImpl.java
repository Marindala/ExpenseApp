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

public class TransactionsDaoImpl {
    private static final String GET_ALL_TRANSACTIONS = "SELECT * FROM transactions";

    private static final String INSERT_INTO_TRANSACTIONS = "INSERT INTO transactions (id, gestorDeCuentaId, categoriaId, fecha, monto) VALUES (25, ?, ?, ?, ?)";
    private static final String GET_TRANSACTIONS_BY_ID = "SELECT * FROM transactions WHERE id = ?";
    private final Connection connection;

    public TransaccionesDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransaccionesDto getById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<TransaccionesDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TRANSACCIONES)) {
            ResultSet resultSet = statement.executeQuery();
            List<TransaccionesDto> transaccionesDtos = new ArrayList<>();
            // Iteramos el ResultSet para agregar el gasto a la lista
            // y mientras agregamos, realizamo el mapeo de cada item
            while (resultSet.next()) {
                transaccionesDtos.add(mapDtoTotransaccionesDto(resultSet));
            }
            return transaccionesDtos;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la lista de Transacciones", e);
        }
    }

    @Override
    public void insert(TransaccionesDto transaccionesDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TRANSACCIONES)) {
            // Mapeo de dto a entidad
            Transacciones transacciones = mapDtoToTransacciones(transaccionesDto);

            statement.setLong(1, transacciones.getGestorDeCuentaId());
            statement.setLong(2, transacciones.getCategoriaId());
            statement.setString(3, transacciones.getFecha());
            statement.setDouble(4, transacciones.getMonto());
            int affectedRows = statement.executeUpdate();
            // Validamos si el resultado de la ejecución del statement no devuelve filas afectadas,
            // entonces hubo un error al insertar en base de datos
            if (affectedRows == 0) {
                throw new DAOException("Error al insertar la Transacción, ninguna fila fue afectada.");
            }
        } catch (DAOException | SQLException e) {
            assert e instanceof SQLException;
            throw new DAOException("Error al insertar la Transacción", (SQLException) e);
        }
    }

    @Override
    public void update(TransaccionesDto transaccionesDto) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }

    private Transacciones mapDtoToTransacciones(TransaccionesDto transaccionesDto) {
        Transacciones transacciones = new Transacciones();
        transacciones.setGestorDeCuentaId(transaccionesDto.getGestorDeCuentaId());
        transacciones.setCategoriaId(transaccionesDto.getCategoriaId());
        transacciones.setFecha(transaccionesDto.getFecha());
        transacciones.setMonto(transaccionesDto.getMonto());
        return transacciones;
    }

    private TransaccionesDto mapDtoTotransaccionesDto(ResultSet resultSet) throws SQLException {
        TransaccionesDto transaccionesDto = new TransaccionesDto();
        transaccionesDto.setGestorDeCuentaId(resultSet.getInt("gestorDeCuentaId"));
        transaccionesDto.setCategoriaId(resultSet.getInt("categoriaId"));
        //transaccionesDto.setFecha(String.valueOf(resultSet.getDate("fecha")));
        transaccionesDto.setFecha(resultSet.getString("fecha"));
        transaccionesDto.setMonto(resultSet.getDouble("monto"));
        return transaccionesDto;
    }
}
