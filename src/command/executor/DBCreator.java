package command.executor;

import command.CommandType;

public class DBCreator extends AbstractCommandExecutor{

    @Override
    public int execute(String command) {
        return createSqlDatabase(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_DATABASE;
    }

    private int createSqlDatabase(String command)
    {
        try
        {
            mySQL.execUpdate(
                    "DROP DATABASE IF EXISTS Clinic;");
            mySQL.execUpdate("CREATE DATABASE Clinic;");
            mySQL.execUpdate("USE Clinic;");
            mySQL.execUpdate("CREATE TABLE Doctor (" +
                    "Doctor_id int NOT NULL AUTO_INCREMENT," +
                    "Name varchar(50) NOT NULL," +
                    "PRIMARY KEY (Doctor_id)" +
                    ");");
            mySQL.execUpdate("CREATE TABLE patient (" +
                    "Patient_id int NOT NULL AUTO_INCREMENT," +
                    "Name varchar(50) NOT NULL," +
                    "RegistrationDate date NOT NULL," +
                    "PRIMARY KEY (Patient_id)" +
                    ");");
            mySQL.execUpdate("CREATE TABLE record (" +
                    "Record_id int NOT NULL AUTO_INCREMENT," +
                    "Patient_id int NOT NULL," +
                    "Doctor_id int NOT NULL," +
                    "Status varchar(50) NOT NULL," +
                    "RecordDate date NOT NULL," +
                    "RecordTime time NOT NULL," +
                    "PRIMARY KEY (Record_id)," +
                    "KEY Patient_id (Patient_id)," +
                    "KEY Doctor_id (Doctor_id)," +
                    "CONSTRAINT record_ibfk_1 FOREIGN KEY (Patient_id) REFERENCES patient (Patient_id)," +
                    "CONSTRAINT record_ibfk_2 FOREIGN KEY (Doctor_id) REFERENCES doctor (Doctor_id)" +
                    ");");
            mySQL.execUpdate("INSERT INTO doctor VALUES (1,'John'),(2,'Susan');");
            mySQL.execUpdate("INSERT INTO patient VALUES (1,'Frank','2020-01-01'),(2,'Hugh','2021-01-01');");
            mySQL.execUpdate("INSERT INTO record VALUES (1,1,1,'Done','2000-01-01','13:00:00')," +
                    "(2,1,2,'Cancelled','2015-01-01','15:00:00');");
            System.out.println("database created");
            return 1;
        }
        catch (Exception e)
        {
            System.out.println("database creation failed");
            return -1;
        }
    }
}
