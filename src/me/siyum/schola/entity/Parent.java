package me.siyum.schola.entity;

public class Parent implements SuperEntity {
    private int id;
    private String name;
    private String email;
    private String nic;
    private String address;
    private String phone;

    public Parent() {
    }

    public Parent(int id, String name, String email, String nic, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
