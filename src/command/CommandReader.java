package command;
import command.executor.*;

import java.util.Map;
import java.util.Scanner;

public class CommandReader {
    private static final Map<CommandType, CommandExecutor> COMMAND_EXECUTORS_GROUPED_BY_COMMAND = Map.of(
            CommandType.CREATE_USER, new UserCreator(),
            CommandType.DELETE_USER, new UserDeleter(),
            CommandType.PRINT_USERS, new UserPrinter(),
            CommandType.RENAME_USER, new UserRenamer(),
            CommandType.CREATE_RECORD, new RecordCreator(),
            CommandType.CHANGE_RECORD, new RecordChanger(),
            CommandType.PRINT_RECORDS, new RecordPrinter()
    );

    /** Stop reading on command "exit". */
    public static void startReading() {
        Scanner s = new Scanner(System.in);

        int i = 1;
        while (i != 0) {
            i = readCommand(s);
        }

        s.close();
    }

    /**
     * Available commands:
     * - "create doctor doctor-name";
     * - "create patient patient-name registration-date", registration-date format - 2022-01-01 ;
     * - "create record patient-id doctor-id status record-date record-time", status - 1 word! \
     *    example ( create record 1 4 In_progress 2022-01-01 15:00 );
     * - "print doctors", view all doctors;
     * - "print patients";
     * - "print records patient-id";
     * - "print records all";
     * - "delete patient patient-name", same for doctors;
     * - "rename patient old-patient-name new-patient-name", same for doctors;
     * - "change status record-id new-status", example ( change status 1 Done );
     * - "exit";
     */
    private static int readCommand(Scanner s)  {
        var commandString = s.nextLine();

        CommandType commandType = getCommandType(commandString); // достаем из строки команду.

        if (COMMAND_EXECUTORS_GROUPED_BY_COMMAND.containsKey(commandType)) { // проверяем, есть ли обработчик этой команды в мапе по ключу.
            var commandExecutor = COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(commandType); // если есть, то достаем обработчик по ключу
            return commandExecutor.execute(commandString); // и выполняем команду
        }

        if (commandType == CommandType.EXIT) {
            return 0;
        }

        System.out.println("Incorrect command");
        return -1;
    }

    private static CommandType getCommandType(String commandString) {
        if (commandString.contains("create record")) {
            return CommandType.CREATE_RECORD;
        }
        else if (commandString.split(" ")[0].equals("create")) {
            return CommandType.CREATE_USER;
        }

        if (commandString.contains("print records")) {
            return CommandType.PRINT_RECORDS;
        }
        else if (commandString.split(" ")[0].equals("print")) {
            return CommandType.PRINT_USERS;
        }

        if (commandString.contains("delete")) {
            return CommandType.DELETE_USER;
        }
        if (commandString.contains("rename")) {
            return CommandType.RENAME_USER;
        }

        if (commandString.contains("change status")) {
            return CommandType.CHANGE_RECORD;
        }

        if (commandString.contains("exit")) {
            return CommandType.EXIT;
        }

        return CommandType.UNDEFINED;
    }
}
