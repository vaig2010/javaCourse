package command.executor;

import command.CommandType;


public class UserCreator extends AbstractCommandExecutor {
    @Override
    public int execute(String command) { return createSqlUsers(command); }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_USER;
    }

    private int createSqlUsers(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName = wordsArray[2];
        try
        {
            // Все подобные записи аналогичны.
            // Если команда (create patient). Создание пациента
            if (userType.equals("patient"))
            {
                var regDate = wordsArray[3];
                mySQL.execUpdate(String.format(
                        "INSERT INTO Patient (Name, RegistrationDate) VALUES ('%s', '%s');",
                        userName,regDate));
                System.out.println("patient created");
            }
            // Если команда (create doctor). Создание доктора
            if (userType.equals("doctor"))
            {
                mySQL.execUpdate(String.format(
                        "INSERT INTO Doctor (Name) VALUES ('%s');",
                        userName));
                System.out.println("doctor created");
            }
            return 1;
        }
        catch (Exception e)
        {
            System.out.println("user creation failed");
            return -1;
        }
    }
}

