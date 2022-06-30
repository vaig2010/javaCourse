package command.executor;

import users.Patient;
import users.Doctor;
import command.CommandType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreator extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return createSqlUsers(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_USER;
    }

    private int createSqlUsers(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName = wordsArray[2];

        if (userType.equals("patient"))
        {
            var regDate = wordsArray[3];
            try
            {
                int answ = mySQL.execUpdate(String.format(
                        "INSERT INTO Patient (Name, RegistrationDate) VALUES ('%s', '%s');",
                        userName,regDate));
                System.out.println("patient created");
            }
            catch (Exception e) { return -1; }
        }
        if (userType.equals("doctor"))
        {
            try
            {
                int answ = mySQL.execUpdate(String.format(
                        "INSERT INTO Doctor (Name) VALUES ('%s');",
                        userName));
                System.out.println("doctor created");
            }
            catch (Exception e) { return -1; }
        }

        return 1;

    }
}
//    private int createUser(String command) {
//        var wordsArray = command.split(" ");
//        var userType = wordsArray[1];
//        var userName = wordsArray[2];
//
//
//        if (userType.equals("doctor"))
//        {
//            if (findDoctor(userName).isPresent())
//            {
//                System.out.println("User already exists");
//                return -1;
//            }
//            var newUser = new Doctor(0,userName);
//            doctorRepository.save(newUser);
//        }
//        else if (userType.equals("patient"))
//        {
//            if (findPatient(userName).isPresent())
//            {
//                System.out.println("patient already exists");
//                return -1;
//            }
//            var regDate = wordsArray[3];
//            var newUser = new Patient(0,userName,regDate);
//            patientRepository.save(newUser);
//        }
//        else return -1;
//        System.out.println("New user created");
//        return 1;
//    }

