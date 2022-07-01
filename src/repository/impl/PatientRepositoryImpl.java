package repository.impl;

import users.AbstractUser;
import users.Patient;

import java.util.HashSet;
import java.util.Set;

public class PatientRepositoryImpl {

    private static final Set<Patient> PATIENTS = new HashSet<>();

    private static final PatientRepositoryImpl SINGLETON = new PatientRepositoryImpl();

    private PatientRepositoryImpl() {}

    public static PatientRepositoryImpl getSingleton() { return SINGLETON; }

    public Set<Patient> findAll() { return PATIENTS; }

    public void save(Patient patient) { PATIENTS.add(patient); }

    public void remove(Patient patient) { PATIENTS.remove(patient); }
}
