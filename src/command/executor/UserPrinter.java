package command.executor;

import repository.impl.PatientRepositoryImpl;
import users.Doctor;
import users.Patient;
import command.CommandType;

import java.util.Optional;

public class UserPrinter extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return printUsers(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.PRINT_USERS;
    }

    private int printUsers(String command) {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        if (userType.equals("doctor"))
        {
            for (Doctor doctor : doctorRepository.findAll()) {
                System.out.printf("Id: \"%s\". Name: \"%s\"%n", doctor.getId(), doctor.getName());
            }
            return 1;
        }
        else if (userType.equals("patient"))
        {
            for (Patient patient : patientRepository.findAll()) {
                System.out.printf("Id: \"%s\". Name: \"%s\". rDate: \"%s\". %n",
                        patient.getId(), patient.getName(), patient.getRegistrationDate());
            }
            return 1;
        }
        else return -1;


    }
}
