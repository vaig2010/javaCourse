package users;

public class Patient extends AbstractUser {
    private final String registrationDate;
    public Patient(int id, String name, String registrationDate) {
        super(id,name);
        this.registrationDate = registrationDate;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
}
