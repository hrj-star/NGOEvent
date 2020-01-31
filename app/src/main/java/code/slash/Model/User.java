package code.slash.Model;

public class User {
    private String email,name,pass,type;

     User() {
    }

    public String getType() {
        return type;
    }


    public User(String email, String name, String pass, String type) {
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
