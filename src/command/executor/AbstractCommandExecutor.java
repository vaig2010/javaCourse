package command.executor;

import repository.impl.DoctorRepositoryImpl;
import users.Doctor;
import java.util.Optional;
import repository.impl.PatientRepositoryImpl;
import users.Patient;

import javax.print.Doc;

public abstract class AbstractCommandExecutor implements CommandExecutor {

    protected final PatientRepositoryImpl patientRepository = PatientRepositoryImpl.getSingleton();
    protected final DoctorRepositoryImpl doctorRepository = DoctorRepositoryImpl.getSingleton();

    protected Optional<Patient> findPatient(String noteName) {
        for (Patient patient : patientRepository.findAll()) {
            if (patient.getName().equals(noteName)) {
                return Optional.of(patient);
            }
        }


        return Optional.empty();
    }
    protected Optional<Doctor> findDoctor(String noteName) {
        for (Doctor doctor : doctorRepository.findAll()) {
            if (doctor.getName().equals(noteName)) {
                return Optional.of(doctor);
            }
        }

        return Optional.empty();
    }
}
