package me.siyum.schola.dto;

public class BatchDTO {
    private String id;
    private String name;
    private double fee;

    public BatchDTO() {
    }

    public BatchDTO(String id, String name, double fee) {
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
