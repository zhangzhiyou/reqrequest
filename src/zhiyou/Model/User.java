package zhiyou.Model;

/**
 * Created by zhiyou on 15-3-11.
 */
public class User {
    private String username;
    private String password;
    public User(){
        super();
    }
    public User (String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
