package com.nc.netcracker_project.server.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "DRUGSTORES")
@XmlRootElement
public class DrugstoreEntity implements Serializable{
    private UUID id;
    private String name;
    private String district;
    private String street;
    private String building;
    private String phone;
    private String workingHours;
    private Short isRoundTheClock;

    public DrugstoreEntity() {
    }

    public DrugstoreEntity(String name, String district, String street, String building, String phone, String workingHours, Short isRoundTheClock) {
        this.name = name;
        this.district = district;
        this.street = street;
        this.building = building;
        this.phone = phone;
        this.workingHours = workingHours;
        this.isRoundTheClock = isRoundTheClock;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "DRUGSTORE_ID", nullable = false, unique = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SNAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "ADDRESS_DISTRICT")
    public String getDistrict() {
        return district;
    }


    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "ADDRESS_STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "ADDRESS_BUILDING")
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "WORKING_HOURS")
    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Basic
    @Column(name = "IS_ROUND_THE_CLOCK")
    public Short getIsRoundTheClock() {
        return isRoundTheClock;
    }

    public void setIsRoundTheClock(Short isRoundTheClock) {
        this.isRoundTheClock = isRoundTheClock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugstoreEntity that = (DrugstoreEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (building != null ? !building.equals(that.building) : that.building != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (workingHours != null ? !workingHours.equals(that.workingHours) : that.workingHours != null) return false;
        return isRoundTheClock != null ? isRoundTheClock.equals(that.isRoundTheClock) : that.isRoundTheClock == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (workingHours != null ? workingHours.hashCode() : 0);
        result = 31 * result + (isRoundTheClock != null ? isRoundTheClock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrugstoreEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", phone=" + phone +
                ", workingHours='" + workingHours + '\'' +
                ", isRoundTheClock=" + isRoundTheClock +
                '}';
    }
}
