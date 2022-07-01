package command.executor;

import command.CommandType;


public class RecordCreator extends AbstractCommandExecutor{
    @Override
    public int execute(String command) {
        return createSqlRecords(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_RECORD;
    }

    private int createSqlRecords(String command)
    {
        var wordsArray = command.split(" ");
        var patientId = Integer.parseInt(wordsArray[2]);
        var doctorId = Integer.parseInt(wordsArray[3]);
        var status = wordsArray[4];
        var recDate = wordsArray[5];
        var recTime = wordsArray[6];
        try
        {
            mySQL.execUpdate(String.format(
                    "INSERT INTO `clinic`.`record` (`Patient_id`, `Doctor_id`, `Status`, `RecordDate`, `RecordTime`)" +
                            " VALUES ('%d', '%d', '%s', '%s', '%s');",
                    patientId,doctorId, status, recDate, recTime));

            System.out.println("record created");
        }
        catch (Exception e) {
            System.out.println("record create failed");
            return -1;
        }
        return 1;
    }
}
