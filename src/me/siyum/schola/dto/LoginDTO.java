package me.siyum.schola.dto;

public class LoginDTO {
    private int id;
    private String token;
    private String role;

    public LoginDTO() {
    }

    public LoginDTO(int id, String token, String role) {
        this.id = id;
        this.token = token;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
