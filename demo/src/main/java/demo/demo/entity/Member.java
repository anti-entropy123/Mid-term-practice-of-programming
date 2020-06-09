package demo.demo.entity;

public class Member {
    private int id;
    private String name;
    private String password;
    private String title;
    private String sex;
    
    public Member(){}
    public Member(int userId, String name, String password, String title, String sex){
        this.id = userId;
        this.name = name;
        this.password = password;
        this.title = title;
        this.sex = sex;
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

    public String getSex(){
        return this.sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
}