package com.demo.domain;

public class CsvDTO {

    private String id;

    private String lastName;

    private String location;

    private String outletName;

    private String outletType;

    public CsvDTO(){

    }

    public CsvDTO(String id, String lastName, String location, String outletName, String outletType) {
        this.id = id;
        this.lastName = lastName;
        this.location = location;
        this.outletName = outletName;
        this.outletType = outletType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletType() {
        return outletType;
    }

    public void setOutletType(String outletType) {
        this.outletType = outletType;
    }

    @Override
    public String toString() {
        return "CsvDTO{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", outletName='" + outletName + '\'' +
                ", outletType='" + outletType + '\'' +
                '}';
    }
}
