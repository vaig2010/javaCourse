package command.executor;

import repository.impl.PatientRepositoryImpl;
import users.Doctor;
import users.Patient;
import command.CommandType;

import java.util.Optional;

public class UserDeleter extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return deleteNote(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_USER;
    }

    private int deleteNote(String command) {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var noteNameToRemove = wordsArray[2];
        if (userType.equals("doctor"))
        {
            Optional<Doctor> noteToRemove = findDoctor(noteNameToRemove);
            if (noteToRemove.isPresent()) {
                doctorRepository.remove(noteToRemove.get());
                System.out.println("User deleted");
            } else {
                System.out.println("User not found");
            }

            return 1;
        }
        else if (userType.equals("patient"))
        {
            Optional<Patient> noteToRemove = findPatient(noteNameToRemove);
            if (noteToRemove.isPresent()) {
                patientRepository.remove(noteToRemove.get());

                System.out.println("User deleted");
            } else {
                System.out.println("User not found");
            }

            return 1;
        }
        else return -1;

    }
}
