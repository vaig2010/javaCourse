package command.executor;

import command.CommandType;


public class UserDeleter extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return deleteSqlUser(command);
    }

    @Override
    public CommandType getCommandType() { return CommandType.DELETE_USER; }

    private int deleteSqlUser(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName = wordsArray[2];
        try
        {
            if (userType.equals("patient"))
            {
                mySQL.execUpdate(String.format(
                        "DELETE FROM Patient WHERE Name = '%s';",
                        userName));
                System.out.println("patient deleted");
            }
            if (userType.equals("doctor"))
            {
                mySQL.execUpdate(String.format(
                        "DELETE FROM Doctor WHERE Name = '%s';",
                        userName));
                System.out.println("doctor deleted");
            }
            return 1;
        }
        catch (Exception e) {
            System.out.println("user deletion failed");
            return -1;
        }
    }
}
