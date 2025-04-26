package socialmedia;

public class User {
    
    private String name;
    private String email;
    private String password;
    private String atSign;

    public User(String name, String email, String password, String atSign) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.atSign = atSign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAtSign() {
        return atSign;
    }

    public void setAtSign(String atSign) {
        this.atSign = atSign;
    }
}
