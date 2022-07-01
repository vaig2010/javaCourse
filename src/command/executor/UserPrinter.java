package command.executor;

import command.CommandType;
import java.sql.ResultSet;

public class UserPrinter extends AbstractCommandExecutor {

    @Override
    public int execute(String command)  { return printSqlUsers(command); }

    @Override
    public CommandType getCommandType() {
        return CommandType.PRINT_USERS;
    }

    private int printSqlUsers(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        try
        {
            if (userType.equals("patients"))
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
            if (userType.equals("doctors"))
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
            return 1;
        }
        catch (Exception e) { return -1; }
    }
}
