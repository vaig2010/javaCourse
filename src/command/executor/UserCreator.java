package command.executor;

import users.Patient;
import users.Doctor;
import command.CommandType;

public class UserCreator extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return createUser(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_USER;
    }

    private int createUser(String command) {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName = wordsArray[2];

        if (findDoctor(userName).isPresent() || findPatient(userName).isPresent()) {
            System.out.println("User already exists");
            return -1;
        }
        if (userType.equals("doctor"))
        {
            var newUser = new Doctor(0,userName);
            doctorRepository.save(newUser);
        }
        else if (userType.equals("patient"))
        {
            var regDate = wordsArray[3];
            var newUser = new Patient(0,userName,regDate);
            patientRepository.save(newUser);
        }
        else return -1;


        System.out.println("New user created");

        return 1;
    }
}
