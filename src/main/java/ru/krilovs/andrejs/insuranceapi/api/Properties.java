package ru.krilovs.andrejs.insuranceapi.api;

import java.util.Set;

public final class Properties {
    public final static String POLICY_PROPERTY = "policy";
    public static final Set<String> ORIGINAL_POLICY_KEYS = Set.of("id","policyObjects","premium","status","user");
    public static final Set<String> ORIGINAL_POLICY_OBJECT_KEYS = Set.of("name","policySubObjects");
    public static final Set<String> ORIGINAL_POLICY_SUB_OBJECT_KEYS = Set.of("name","sum","riskType");
}
