package users;

public class Patient extends AbstractUser {
    private String registrationDate;
    public Patient(int id, String name, String registrationDate) {
        super(id,name);
        this.registrationDate = registrationDate;
    }
    public Patient() {}
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String regDate) {
        this.registrationDate = regDate;
    }

}
