package me.siyum.schola.entity;

import java.sql.Blob;
import java.time.LocalDate;

public class Student implements SuperEntity {
    private String gender;
    private LocalDate created;
    private String id;
    private String name;
    private String email;
    private String nic;
    private Blob image;
    private String address;
    private String phone;
    private String parentID;
    private int scholaMark;
    private String batch;
    private LocalDate dob;
    private boolean status;
    private boolean approval;


    public Student(String id, String name, String email, String nic,
                   Blob image, String address, String phone, String parentID,
                   int scholaMark, LocalDate dob, boolean status, boolean approval, String batch, String gender, LocalDate created) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.parentID = parentID;
        this.scholaMark = scholaMark;
        this.dob = dob;
        this.status = status;
        this.approval = approval;
        this.batch = batch;
        this.gender = gender;
        this.created = created;
    }


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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
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

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public int getScholaMark() {
        return scholaMark;
    }

    public void setScholaMark(int scholaMark) {
        this.scholaMark = scholaMark;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
