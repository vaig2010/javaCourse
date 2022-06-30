import database.MySqlDatabase;
import users.Doctor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import command.CommandReader;
import users.Patient;

public class Main {
    public static void main(String[] args) throws SQLException {
//
//        Authentication authentication = new Authentication();
//        authentication.authenticate();
        CommandReader.startReading();
        MySqlDatabase mySQL = MySqlDatabase.getSingleton();
        mySQL.getConnect().close();
    }
}