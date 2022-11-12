package me.siyum.schola.dto;

import me.siyum.schola.entity.SuperEntity;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

public class StudentDTO implements SuperEntity {
    private int id;
    private String name;
    private String email;
    private String nic;
    private Blob image;
    private String address;
    private String phone;
    private int parentID;
    private int scholaMark;
    private int batch;
    private LocalDate dob;
    private boolean status;
    private boolean approval;


    public StudentDTO(int id, String name, String email, String nic, Blob image, String address, String phone, int parentID, int scholaMark, LocalDate dob, boolean status, boolean approval, int batch) {
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
    }

    public StudentDTO() {
    }

    public StudentDTO(int id, String name, String email, String nic, Blob image, String address, String phone, int parentID, int scholaMark) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.parentID = parentID;
        this.scholaMark = scholaMark;
    }

    public StudentDTO(int id, String name, String email, String nic, String address, Blob image, String phone, int parentID, int scholaMark) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.parentID = parentID;
        this.scholaMark = scholaMark;
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

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
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

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
}
