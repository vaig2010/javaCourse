package command.executor;

import users.Doctor;
import users.Patient;
import command.CommandType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPrinter extends AbstractCommandExecutor {

    @Override
    public int execute(String command)  {
        return printSqlPatients(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.PRINT_USERS;
    }

    private int printUsers(String command) {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        if (userType.equals("doctor"))
        {
            for (Doctor doctor : doctorRepository.findAll()) {
                System.out.printf("Id: \"%s\". Name: \"%s\"%n", doctor.getId(), doctor.getName());
            }
            return 1;
        }
        else if (userType.equals("patient"))
        {
            for (Patient patient : patientRepository.findAll()) {
                System.out.printf("Id: \"%s\". Name: \"%s\". rDate: \"%s\". %n",
                        patient.getId(), patient.getName(), patient.getRegistrationDate());
            }
            return 1;
        }
        else return -1;
    }

    private int printSqlPatients(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        if (userType.equals("patients"))
        {
            try
            {
                ResultSet resultSet = mySQL.query("Select * from Patient");
                while (resultSet.next())
                {
                    System.out.printf("Id: \"%s\". Name: \"%s\". rDate: \"%s\". %n",
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3));
                }
                resultSet.close();
            }
            catch (Exception e)
            {
                return -1;
            }
        }
        if (userType.equals("doctors"))
        {
            try
            {
                ResultSet resultSet = mySQL.query("Select * from Doctor");
                while (resultSet.next())
                {
                    System.out.printf("Id: \"%s\". Name: \"%s\". %n",
                            resultSet.getInt(1),
                            resultSet.getString(2));
                }
                resultSet.close();
            }
            catch (Exception e)
            {
                return -1;
            }
        }
        return 1;

    }
}
