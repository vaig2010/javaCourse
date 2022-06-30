package database;

import repository.impl.DoctorRepositoryImpl;
import repository.impl.PatientRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlDatabase {
    String username = "igor";
    String password = "12345";
    String url = "jdbc:mysql://localhost:3306/Clinic";
    Connection connection = DriverManager.getConnection(url,username,password);
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

    public static Connection connect(String url, String username, String password)
    {
        try
        {
            return DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from Doctor");
//            while (resultSet.next())
//            {
//                System.out.println(resultSet.getString(2));
//            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public Connection getConnect()
    {
        try
        {
            return DriverManager.getConnection(this.url,this.username,this.password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet query(String q) throws SQLException
    {
        Statement statement = connection.createStatement();
        return statement.executeQuery(q);
    }
    public int execUpdate(String q) throws SQLException
    {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(q);
    }

}
