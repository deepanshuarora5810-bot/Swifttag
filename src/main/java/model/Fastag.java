package model;

import java.time.LocalDate;

public class Fastag {
    private int fastagId;
    private String tagNumber;
    private int vehicleId;
    private LocalDate issueDate;
    private String status;

    public Fastag() {}

    public Fastag(int fastagId, String tagNumber, int vehicleId,
                  LocalDate issueDate, String status) {
        this.fastagId = fastagId;
        this.tagNumber = tagNumber;
        this.vehicleId = vehicleId;
        this.issueDate = issueDate;
        this.status = status;
    }

    public int getFastagId() { return fastagId; }
    public void setFastagId(int fastagId) { this.fastagId = fastagId; }
    public String getTagNumber() { return tagNumber; }
    public void setTagNumber(String tagNumber) { this.tagNumber = tagNumber; }
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "FASTag Details:\nFASTag ID: " + fastagId +
               "\nTag Number: " + tagNumber +
               "\nVehicle ID: " + vehicleId +
               "\nIssue Date: " + issueDate +
               "\nStatus: " + status;
    }
}
