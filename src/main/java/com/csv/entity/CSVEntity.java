package com.csv.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "CSV_Data")
public class CSVEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @NotNull
    @Column(name="ID")
    private String id;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="LOCATION")
    private String location;

    @Column(name="OUTLET_NAME")
    private String outletName;

    @Column(name="OUTLET_TYPE")
    private String outletType;

    public CSVEntity() {
    }

    public CSVEntity(@NotNull String id, String lastName, String location, String outletName, String outletType) {
        super();
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
        return "CSVEntity [id=" + id + ", lastName=" + lastName
                + ", location=" + location + ", outletName=" + outletName + ", outletType=" + outletType + "]";
    }
}