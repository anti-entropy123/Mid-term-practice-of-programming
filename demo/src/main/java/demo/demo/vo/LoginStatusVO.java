package demo.demo.vo;

import demo.demo.entity.Member;

public class LoginStatusVO implements ViewObject{
    private int userId;
    private String username;
    private String title;

    public LoginStatusVO(){};
    public LoginStatusVO(int userId, String username, String title){
        this.userId = userId;
        this.username = username;
        this.title = title;
    }     
    public LoginStatusVO(Member m){
        this.userId = m.getId();
        this.username = m.getName();
        this.title = m.getTitle();
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

}