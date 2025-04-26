package socialmedia;

public class Mensage {
    
    private String value;
    private User user;
    private int idMensage;
    private Integer idMensageFather;

    public Mensage(String value, User user, Integer idMensageFather) {

        this.value = value;
        this.user = user;
        this.idMensageFather = idMensageFather;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    } 

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 

    public Integer getIdMensageFather() {
        return idMensageFather;
    }

    public void setIdMensageFather(int idMensageFather) {
        this.idMensageFather = idMensageFather;
    } 

    public int getIdMensage() {
        return idMensage;
    }

    public void setIdMensage(int idMensage) {
        this.idMensage = idMensage;
    }
}
