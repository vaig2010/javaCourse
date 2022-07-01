package command.executor;

import command.CommandType;


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
        try
        {
            if (userType.equals("patient"))
            {
                mySQL.execUpdate(String.format(
                        "UPDATE Patient SET Name = '%s' WHERE Name = '%s';",
                        userName2, userName1));
                System.out.println("patient renamed");
            }
            if (userType.equals("doctor"))
            {
                mySQL.execUpdate(String.format(
                        "UPDATE Doctor SET Name = '%s' WHERE Name = '%s';",
                        userName2, userName1));
                System.out.println("doctor renamed");
            }
            return 1;
        }
        catch (Exception e)
        {
            System.out.println("user renaming failed");
            return -1;
        }
    }
}
