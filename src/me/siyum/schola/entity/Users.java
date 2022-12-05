package me.siyum.schola.entity;

public class Users implements SuperEntity {
    private String id;
    private String userName;
    private String password;
    private String role;
    private String eID;

    public Users() {
    }

    public Users(String id, String userName, String password, String role, String eID) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.eID = eID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }
}
