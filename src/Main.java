import database.MySqlDatabase;
import java.sql.SQLException;
import command.CommandReader;

public class Main {
    public static void main(String[] args) throws SQLException {
        // login: admin
        // password: admin
        Authentication authentication = new Authentication();
        authentication.authenticate();
        MySqlDatabase mySQL = MySqlDatabase.getSingleton();
        // OR
        // var conn = mySQL.getConnect("jdbc:mysql://localhost:3306/", "user", "password");
        var conn = mySQL.getConnect();
        CommandReader.startReading();
        conn.close();
    }
}