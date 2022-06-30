package command;

// В енамах перечисляются в основном какие-то константы, ключевые слова и тп. В данном случае перечислен фиксированный
// заранее определенный список команд. С енамами просто иногда удобнее работать, чем со строками.
public enum CommandType {

    CREATE_USER,
    DELETE_USER,
    PRINT_USERS,
    RENAME_USER,

    EXIT,

    UNDEFINED
}
