package command.executor;

import command.CommandType;
import java.sql.ResultSet;

public class RecordPrinter extends AbstractCommandExecutor{
    @Override
    public int execute(String command) { return printSqlRecords(command); }

    @Override
    public CommandType getCommandType() {
        return CommandType.PRINT_RECORDS;
    }

    private int printSqlRecords(String command)
    {
        var wordsArray = command.split(" ");
        String patientId = wordsArray[2];
        try
        {
            // вывод всех записей
            if (patientId.equals("all"))
            {
                ResultSet resultSet = mySQL.query("Select * from Record");
                while (resultSet.next())
                {
                    System.out.printf("recId: \"%d\". patId: \"%d\". docId: \"%d\"." +
                                    " status: \"%s\". recDate: \"%s\". recTime: \"%s\". %n",
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );
                }
                resultSet.close();
            }
            // вывод записей конкретного пациента по параметру patientId
            else {
                ResultSet resultSet = mySQL.query(String.format("SELECT * FROM Record WHERE Patient_id = '%s'",
                                                                                                    patientId));
                while (resultSet.next()) {
                    System.out.printf("recId: \"%d\". patId: \"%d\". docId: \"%d\"." +
                                    " status: \"%s\". recDate: \"%s\". recTime: \"%s\". %n",
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );
                }
            }
            return 1;
        }
        catch (Exception e) { return -1; }
    }
}
