package me.siyum.schola.dto;

import java.sql.Blob;

public class EmployeeDTO {
    private String id;
    private Blob image;
    private String name;
    private String address;
    private String email;
    private double salary;
    private String paymentMethod;
    private String role;
    private boolean status;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, Blob image, String name, String address, String email, double salary, String paymentMethod, String role, boolean status) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.address = address;
        this.email = email;
        this.salary = salary;
        this.paymentMethod = paymentMethod;
        this.role = role;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
