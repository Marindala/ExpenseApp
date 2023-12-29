package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConfiguration {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/expense";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Conexión a la base de datos establecida con éxito.");
             //Crear una declaración SQL
            Statement statement = connection.createStatement();
            //String query = "INSERT INTO empleados (id, nombre, salario) VALUES (?, ?, ?)";
            //PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Crear la tabla
            //String createTableQuery = "CREATE TABLE IF NOT EXISTS gastos (id INT PRIMARY KEY, nombre VARCHAR(50))";
            //statement.executeUpdate(createTableQuery);
            // Insertar registros
            //String insertQuery = "INSERT INTO usuarios VALUES (1, 'comida'), (2, 'vestimenta')";
           // statement.executeUpdate(insertQuery);
            // Cerrar la conexión
            //statement.close();
            //connection.close();
            //return connection;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return connection;
    }


}

