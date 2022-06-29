package users;

public class Doctor {
    private final Integer id;
    private final String name;

    public Doctor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

}
