package main.java.Resources;

public class Credentials {

    private final String email;
    private final String pass;

    public Credentials(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
