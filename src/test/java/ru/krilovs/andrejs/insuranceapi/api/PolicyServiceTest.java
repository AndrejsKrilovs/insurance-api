package ru.krilovs.andrejs.insuranceapi.api;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.krilovs.andrejs.insuranceapi.entity.*;
import ru.krilovs.andrejs.insuranceapi.service.PolicyService;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PolicyServiceTest {
    @Autowired
    private PolicyController controller;

    @MockBean
    private PolicyService service;

    @Test
    public void getAllPoliciesTest() {
        when(service.getAllPolicies())
                .thenReturn(List.of(
                        initTestPolicy("Test user 1"),
                        initTestPolicy("Test user 2")
                ));
        assertEquals(2, Objects.requireNonNull(controller.policyList().getBody()).size());
    }

    @Test
    public void getPolicyTest() {
        Policy policyForTest = initTestPolicy("Test user 1");
        String testPolicyNumber = "LV19-07-100000-1";
        when(service.getPolicy(testPolicyNumber))
                .thenReturn(policyForTest);
        assertNotNull(controller.showPolicy(testPolicyNumber));
        assertEquals(
                policyForTest.getPolicyNumber(),
                Objects.requireNonNull(controller.showPolicy(testPolicyNumber).getBody()).getPolicyNumber()
        );
    }

    @Test
    public void calculatePremiumTest()  {
        Policy policyForTest = initTestPolicy("Test user 1");
        String testPolicyNumber = "LV19-07-100000-1";
        when(service.getPolicy(testPolicyNumber))
                .thenReturn(policyForTest);
        assertNotNull(controller.showPolicy(testPolicyNumber));
        assertEquals(
                policyForTest.getPolicyPremium(),
                Objects.requireNonNull(controller.showPolicy(testPolicyNumber).getBody()).getPolicyPremium()
        );
    }

    private Policy initTestPolicy(String policyUserName) {
        Policy policy = new Policy();
        PolicyObject policyObject = new PolicyObject();
        PolicySubObject fireSubObject = new PolicySubObject();
        PolicySubObject waterSubObject = new PolicySubObject();

        fireSubObject.setName("Sub object 1");
        fireSubObject.setInsuredSum(Double.valueOf(1));
        fireSubObject.setRiskType(RiskType.FIRE);

        waterSubObject.setName("Sub object 1");
        waterSubObject.setInsuredSum(Double.valueOf(1));
        waterSubObject.setRiskType(RiskType.WATER);

        policyObject.setName("Object");
        policyObject.setSubObjects(List.of(fireSubObject, waterSubObject));

        policy.setUserName(policyUserName);
        policy.setPolicyStatus(Status.APPROVE);
        policy.setPolicyObjects(List.of(policyObject));

        return policy;
    }
}