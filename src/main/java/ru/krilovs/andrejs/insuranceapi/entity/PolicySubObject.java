package ru.krilovs.andrejs.insuranceapi.entity;

public class PolicySubObject {
    private String name;
    private RiskType riskType;
    private Double insuredSum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }

    public Double getInsuredSum() {
        return insuredSum;
    }

    public void setInsuredSum(Double insuredSum) {
        this.insuredSum = insuredSum;
    }
}
