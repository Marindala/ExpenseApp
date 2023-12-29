package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AppExpense {
    public static void main(String[] args) {
         String DB_DRIVER = "org.h2.Driver";
         String DB_CONNECTION = "jdbc:h2:~/expense";
         String DB_USER = "sa";
         String DB_PASSWORD = "1234";
        try {
            Class.forName(DB_DRIVER); // Carga explícitamente el controlador JDBC
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            // Crear una declaración SQL
            Statement statement = connection.createStatement();

            // Crear la tabla
            String createTableQuery = "CREATE TABLE IF NOT EXISTS expense (id INT PRIMARY KEY, nombre VARCHAR(50))";
            statement.executeUpdate(createTableQuery);

            // Insertar registros
            String insertQuery = "INSERT INTO expense VALUES (1, 'food'), (2, 'outfit')";
            statement.executeUpdate(insertQuery);

            // Cerrar la conexión
            statement.close();
            connection.close();

            System.out.println("Registros insertados con éxito en la tabla 'expense'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
