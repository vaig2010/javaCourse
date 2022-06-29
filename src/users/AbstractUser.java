package users;

public abstract class AbstractUser {
    private final int id;
    private final String name;

    public AbstractUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
