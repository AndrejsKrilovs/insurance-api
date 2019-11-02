package ru.krilovs.andrejs.insuranceapi.entity;

import java.util.List;

/**
 * TODO: @svisotsky to @akrilovs Take a look at builder pattern
 */
public class PolicyObject {
    private String name;
    private List<PolicySubObject> subObjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PolicySubObject> getSubObjects() {
        return subObjects;
    }

    public void setSubObjects(List<PolicySubObject> subObjects) {
        this.subObjects = subObjects;
    }
}
