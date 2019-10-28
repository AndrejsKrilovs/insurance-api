package ru.krilovs.andrejs.insuranceapi.entity;

import java.util.List;

public class Policy {
    private String id;
    private Status status;
    private Double premium;
    private List<PolicyObject> policyObjects;

    public Policy() {
    }

    public Policy(String id, Status status, Double premium, List<PolicyObject> policyObjects) {
        this.id = id;
        this.status = status;
        this.premium = premium;
        this.policyObjects = policyObjects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }

    public void setPolicyObjects(List<PolicyObject> policyObjects) {
        this.policyObjects = policyObjects;
    }
}
