package demo.demo.entity;

import org.springframework.lang.Nullable;

public class Member {
    private int id;
    private String name;
    private String password;
    private String title;
    
    public Member(){}
    public Member(@Nullable int userId, String name, String password, String title) {
        this.id = userId;
        this.name = name;
        this.password = password;
        this.title = title;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    } 

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}