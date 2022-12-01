package me.siyum.schola.entity;

public class Batch implements SuperEntity {
    private String id;
    private String name;
    private double fee;

    public Batch() {
    }

    public Batch(String id, String name, double fee) {
        this.id = id;
        this.name = name;
        this.setFee(fee);
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
