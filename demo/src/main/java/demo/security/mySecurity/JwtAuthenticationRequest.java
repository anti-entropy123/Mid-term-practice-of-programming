package demo.security.mySecurity;

import java.io.Serializable;


public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
        System.out.println("***************** JwtAuthenticationRequest 无参构造  **********************");
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        System.out.println(String.format("***************** JwtAuthenticationRequest 有参构造 %s %s  **********************", username, password));
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
