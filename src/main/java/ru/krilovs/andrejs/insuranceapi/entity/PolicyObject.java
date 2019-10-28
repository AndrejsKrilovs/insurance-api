package ru.krilovs.andrejs.insuranceapi.entity;

public class PolicyObject {
    private String objectName;

    public PolicyObject() {
    }

    public PolicyObject(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
