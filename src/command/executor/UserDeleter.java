package command.executor;

import users.Doctor;
import users.Patient;
import command.CommandType;

import java.sql.ResultSet;
import java.util.Optional;

public class UserDeleter extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return deleteSqlUser(command);
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

    private int deleteSqlUser(String command)
    {
        var wordsArray = command.split(" ");
        var userType = wordsArray[1];
        var userName = wordsArray[2];

        if (userType.equals("patient"))
        {
            try
            {
                mySQL.execUpdate(String.format(
                        "DELETE FROM Patient WHERE Name = '%s';",
                        userName));
                System.out.println("patient deleted");
            }
            catch (Exception e) { return -1; }
        }
        if (userType.equals("doctor"))
        {
            try
            {
                mySQL.execUpdate(String.format(
                        "DELETE FROM Doctor WHERE Name = '%s';",
                        userName));
                System.out.println("doctor deleted");
            }
            catch (Exception e) { return -1; }
        }
        return 1;
    }
}
