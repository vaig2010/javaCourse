package command.executor;

import command.CommandType;

import java.sql.SQLException;

public class UserRenamer extends AbstractCommandExecutor{

    @Override
    public int execute(String command){
        return renameSqlUser(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.RENAME_USER;
    }

    private int renameSqlUser(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName1 = wordsArray[2];
        var userName2 = wordsArray[3];

        if (userType.equals("patient"))
        {
            try
            {
                mySQL.execUpdate(String.format(
                        "UPDATE Patient SET Name = '%s' WHERE Name = '%s';",
                        userName2, userName1));
                System.out.println("patient renamed");
            }
            catch (Exception e) { return -1; }
        }
        if (userType.equals("doctor"))
        {
            try
            {
                mySQL.execUpdate(String.format(
                        "UPDATE Doctor SET Name = '%s' WHERE Name = '%s';",
                        userName2, userName1));
                System.out.println("doctor renamed");
            }
            catch (Exception e) { return -1; }
        }
        return 1;
    }
}
