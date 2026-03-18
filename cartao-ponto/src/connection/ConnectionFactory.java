package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL      = "jdbc:mysql://localhost:3306/cartao_ponto?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USER     = "root";   // ← troque pelo seu usuário
    private static final String PASSWORD = "Root";   // ← troque pela sua senha

    private ConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}