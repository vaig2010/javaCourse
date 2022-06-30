package users;

public abstract class AbstractUser {
    private int id;
    private String name;

    public AbstractUser(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public AbstractUser()
    {

    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {this.name = name;}
    public void setId(int id) {this.id = id;}
}
