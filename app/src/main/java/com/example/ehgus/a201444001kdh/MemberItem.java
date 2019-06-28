package com.example.ehgus.a201444001kdh;

public class MemberItem {


    String id;
    String name;
    String pw;
    String email;

    public MemberItem(String id, String pw, String name, String email) {

        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }
    
    // int memId, String id, String name, String pw, String email를 매개변수로 하는 MemberItem 생성자 생성
    
    
    
    
    
    
    



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
