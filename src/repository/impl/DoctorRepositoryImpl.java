package repository.impl;

import users.Doctor;

import java.util.HashSet;
import java.util.Set;

public class DoctorRepositoryImpl {

    private static final Set<Doctor> DOCTORS = new HashSet<>();

    private static final DoctorRepositoryImpl SINGLETON = new DoctorRepositoryImpl();   // Используем паттерн singleton,
    // то есть когда мы создаем внутри класса ровно 1 объект
    // на все приложение и потом выдаем его другим классам, чтобы они его использовали.
    // При этом прячем конструктор, делая его приватным.

    private DoctorRepositoryImpl() {}

    public static DoctorRepositoryImpl getSingleton() {
        return SINGLETON;
    }


    public Set<Doctor> findAll() {
        return DOCTORS;
    }


    public void save(Doctor doctor) {
        DOCTORS.add(doctor);
    }

    public void remove(Doctor doctor) {DOCTORS.remove(doctor);}
}
