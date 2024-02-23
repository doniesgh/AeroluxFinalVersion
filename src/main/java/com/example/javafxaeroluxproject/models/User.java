package com.example.javafxaeroluxproject.models;

public class User {
    private int id ;
    private String first_name , last_name ,email , password , role  ;

    public User() {
    }

    public User(int id, String first_name, String last_name, String email, String password, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String first_name, String last_name, String email, String password, String role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "{User informations : " + "\n"+
                "Id : " + id +"\n"+
                "First name : " + first_name +"\n"+
                "Last name : " + last_name +"\n"+
                "Email : " + email + "\n"+
                "Password : " + password +"\n"+
                "Role : " + role +"\n" + "}"
                ;
    }
}
