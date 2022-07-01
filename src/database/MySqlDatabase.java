package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlDatabase {
    String username = "igor";
    String password = "12345";
    String url = "jdbc:mysql://localhost:3306/Clinic";
    Connection connection;
    private static final MySqlDatabase SINGLETON;   // Используем паттерн singleton,

    static {
        try {
            SINGLETON = new MySqlDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private MySqlDatabase() throws SQLException {}

    public static MySqlDatabase getSingleton() {return SINGLETON;}

    public Connection getConnect(String url, String username, String password)
    {
        try {
            connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Connection getConnect()
    {
        try {
            connection = DriverManager.getConnection(this.url,this.username,this.password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet query(String q) throws SQLException
    {
        Statement statement = connection.createStatement();
        return statement.executeQuery(q);
    }
    public int execUpdate(String q) throws SQLException
    {
        Statement statement = connection.createStatement();
        // По-хорошему нужно проверять что возвращается, но всегда при вызове метода используется try catch
        // Поэтому при неправильном выполнении запроса в любом случае ловим исключение (вроде)
        return statement.executeUpdate(q);
    }

}
