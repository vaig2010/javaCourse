package repository.impl;

import users.Doctor;

import java.util.HashSet;
import java.util.Set;

public class DoctorRepositoryImpl {

    private static final Set<Doctor> DOCTORS = new HashSet<>();

    private static final DoctorRepositoryImpl SINGLETON = new DoctorRepositoryImpl();

    private DoctorRepositoryImpl() {}

    public static DoctorRepositoryImpl getSingleton() { return SINGLETON; }


    public Set<Doctor> findAll() {
        return DOCTORS;
    }


    public void save(Doctor doctor) {
        DOCTORS.add(doctor);
    }

    public void remove(Doctor doctor) {DOCTORS.remove(doctor);}
}
