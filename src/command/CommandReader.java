package command;
import command.executor.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class CommandReader {

    // Map.of - метод, позволяющий инициализировать мапу сразу с данными в ней.
    // Ключем в этой мапе выступает CommandType, то есть какая-то команда (создание заметки, ее удаление, и тп).
    // Значением выступает обработчик этой команды.

    // Таким образом, можем за О(1) найти обработчик по команде. Не то что бы это сильно быстрее, чем просто перебрать все обработчики за О(n)
    // при таком количестве обработчиков :)

    // Рекомендую почитать подробнее про внутреннюю работу HashMap, TreeMap и тп.
    private static final Map<CommandType, CommandExecutor> COMMAND_EXECUTORS_GROUPED_BY_COMMAND = Map.of(
            CommandType.CREATE_USER, new UserCreator(),
            CommandType.DELETE_USER, new UserDeleter(),
            CommandType.PRINT_USERS, new UserPrinter(),
            CommandType.RENAME_USER, new UserRenamer()
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
     * - "create note note-name note text", note-name - only 1 word, note text - 1 or more words;
     * - "delete note note-name";
     * - "notes" - to view all notes.
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
        if (commandString.contains("create")) {
            return CommandType.CREATE_USER;
        }

        if (commandString.contains("delete")) {
            return CommandType.DELETE_USER;
        }

        if (commandString.contains("print")) {
            return CommandType.PRINT_USERS;
        }

        if (commandString.contains("rename")) {
            return CommandType.RENAME_USER;
        }

        if (commandString.contains("exit")) {
            return CommandType.EXIT;
        }

        return CommandType.UNDEFINED;
    }
}
