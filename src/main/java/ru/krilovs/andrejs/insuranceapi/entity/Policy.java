package ru.krilovs.andrejs.insuranceapi.entity;

import java.util.List;

public class Policy {
    private String userName;
    private String policyNumber;
    private Status policyStatus;
    private List<PolicyObject> policyObjects;
    private Double policyPremium;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Status getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(Status policyStatus) {
        this.policyStatus = policyStatus;
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }

    public void setPolicyObjects(List<PolicyObject> policyObjects) {
        this.policyObjects = policyObjects;
    }

    public Double getPolicyPremium() {
        return policyPremium;
    }

    public void setPolicyPremium(Double policyPremium) {
        this.policyPremium = policyPremium;
    }
}
