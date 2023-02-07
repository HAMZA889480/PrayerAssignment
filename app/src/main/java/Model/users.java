package Model;

public class users {

    private int id;
    private String usr_name;
    private String password;


    public users(String user, String Password)
    {
        this.usr_name=user;
        this.password=Password;
    }

    public users()
    {
        this.usr_name="";
        this.password="";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsr_name() {
        return usr_name;
    }
}




