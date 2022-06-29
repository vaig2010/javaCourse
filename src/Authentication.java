
import java.util.Scanner;

public class Authentication {
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    public void authenticate() {
        Scanner s = new Scanner(System.in);

        boolean isAuthenticationSuccess = false;
        for (int i = 3; i != 0 && !isAuthenticationSuccess; i--) {
            String login;
            String password;

            System.out.print("Login: ");
            login = s.nextLine();

            System.out.print("Password: ");
            password = s.nextLine();

            if (validate(login, password)) {
                isAuthenticationSuccess = true;
            } else {
                System.out.println("Password is incorrect. Please try again. ");
            }
        }

        if (!isAuthenticationSuccess) {
            throw new RuntimeException("main.Authentication failed");
        }
    }

    public boolean validate(String login, String password) {
        return login.equals(LOGIN) && password.equals(PASSWORD);
    }
}
