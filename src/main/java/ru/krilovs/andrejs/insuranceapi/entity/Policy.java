package ru.krilovs.andrejs.insuranceapi.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO: @svisotsky to @akrilovs Take a look at builder pattern
 */
public class Policy {
    private String userName;
    private String policyNumber;
    private Status policyStatus;
    private List<PolicyObject> policyObjects;
    private BigDecimal policyPremium;

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

    public BigDecimal getPolicyPremium() {
        return policyPremium;
    }

    public void setPolicyPremium(BigDecimal policyPremium) {
        this.policyPremium = policyPremium;
    }
}
