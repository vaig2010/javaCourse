package command.executor;

import command.CommandType;

import java.sql.SQLException;

public interface CommandExecutor {

    int execute(String command);

    CommandType getCommandType();
}
