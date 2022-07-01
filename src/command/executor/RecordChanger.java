package command.executor;

import command.CommandType;

public class RecordChanger extends AbstractCommandExecutor{

    @Override
    public int execute(String command) { return createSqlRecords(command); }

    @Override
    public CommandType getCommandType() {
        return CommandType.CHANGE_RECORD;
    }

    private int createSqlRecords(String command)
    {
        var wordsArray = command.split(" ");
        var recordId = Integer.parseInt(wordsArray[2]);
        var status = wordsArray[3];
        try
        {
            mySQL.execUpdate(String.format(
                    "UPDATE Record SET Status = '%s' WHERE Record_id = '%d';",
                    status, recordId));
            System.out.println("record status changed");
        }
        catch (Exception e) {
            System.out.println("record status change failed");
            return -1;
        }
        return 1;
    }
}
