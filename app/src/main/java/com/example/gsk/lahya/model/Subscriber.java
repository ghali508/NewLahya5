package com.example.gsk.lahya.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Subscriber extends RealmObject implements Serializable {
    @PrimaryKey
    private String subNo;

    private String subName;
    private String buildingNo;
    private String locationNo;
    private String streetNo;
    private String status;
    private String preReading;
    private String preReadingDate;
    private String currentReading;
    private String currentReadingDate;

    public Subscriber() {
    }

    public Subscriber(String subNo, String subName, String buildingNo, String locationNo, String streetNo, String status, String preReading, String preReadingDate, String currentReading, String currentReadingDate) {
        this.subNo = subNo;
        this.subName = subName;
        this.buildingNo = buildingNo;
        this.locationNo = locationNo;
        this.streetNo = streetNo;
        this.status = status;
        this.preReading = preReading;
        this.preReadingDate = preReadingDate;
        this.currentReading = currentReading;
        this.currentReadingDate = currentReadingDate;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreReading() {
        return preReading;
    }

    public void setPreReading(String preReading) {
        this.preReading = preReading;
    }

    public String getPreReadingDate() {
        return preReadingDate;
    }

    public void setPreReadingDate(String preReadingDate) {
        this.preReadingDate = preReadingDate;
    }

    public String getCurrentReading() {
        return currentReading;
    }

    public String getCurrentReadingDate() {
        return currentReadingDate;
    }

    public void setCurrentReading(String currentReading) {
        this.currentReading = currentReading;
    }

    public void setCurrentReadingDate(String currentReadingDate) {
        this.currentReadingDate = currentReadingDate;
    }

    @Override
    public String toString() {
        return subNo + "," + subName + "," + buildingNo + "," + locationNo + "," + streetNo + "," + status + "," + preReading + "," + preReadingDate + "," + currentReading + "," + currentReadingDate;
    }
}
