package demo.demo.requestbody;

import java.io.Serializable;

public class JwtRegisteRequest implements Serializable{
    private static final long serialVersionUID = -8445943548965135179L;

    private String username;
    private String password;
    private String sex;
    private String title;

    public JwtRegisteRequest(){
        super();
    }
    public JwtRegisteRequest(String username, String password, String sex, String title){
        this.username = username;
        this.password = password;
        if(sex.equals("male") || sex.equals("female")){
            this.sex = sex;
        }else{
            throw new RuntimeException("error sex infomation");
        }
        this.title = title;
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

    public String getSex(){
        return this.sex;
    }
    public void setSex(String sex){
        if(sex.equals("male") || sex.equals("female")){
            this.sex = sex;
        }else{
            throw new RuntimeException("error sex infomation");
        }
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}