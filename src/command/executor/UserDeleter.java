package command.executor;

import users.Doctor;
import users.Patient;
import command.CommandType;

import java.sql.ResultSet;
import java.util.Optional;

public class UserDeleter extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return deleteSqlUser(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_USER;
    }

    private int deleteSqlUser(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName = wordsArray[2];

        if (userType.equals("patient"))
        {
            try
            {
                mySQL.execUpdate(String.format(
                        "DELETE FROM Patient WHERE Name = '%s';",
                        userName));
                System.out.println("patient deleted");
            }
            catch (Exception e) {
                System.out.println("patient delete failed");
                return -1;
            }
        }
        if (userType.equals("doctor"))
        {
            try
            {
                mySQL.execUpdate(String.format(
                        "DELETE FROM Doctor WHERE Name = '%s';",
                        userName));
                System.out.println("doctor deleted");
            }
            catch (Exception e) {
                System.out.println("doctor delete failed");
                return -1;
            }
        }
        return 1;
    }
}
