package ru.krilovs.andrejs.insuranceapi.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ru.krilovs.andrejs.insuranceapi.entity.*;

import java.util.List;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class PolicyControllerTest {
    private final TestRestTemplate restTemplate;

    @Autowired
    public PolicyControllerTest() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void addPolicy() {
        Policy policyForTest = initTestPolicy();
        ResponseEntity<Policy> result = restTemplate
                .postForEntity("http://localhost:8080/policy", new HttpEntity<>(policyForTest), Policy.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(policyForTest.getUserName(), result.getBody().getUserName());
    }

    @Test
    public void getCurrentPolicy() {
        ResponseEntity<Policy> result = restTemplate
                .getForEntity("http://localhost:8080/policy/LV19-07-100000-1",  Policy.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getTotalPoliciesTest() {
        ResponseEntity<Policy[]> result = restTemplate
                .getForEntity("http://localhost:8080/policy", Policy[].class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertTrue(result.getBody().length > 0);
    }

    @Test
    public void updatePolicyTest() {
        Policy newTestPolicyForTest = initTestPolicy();
        newTestPolicyForTest.setUserName("Another user");
        newTestPolicyForTest.setPolicyStatus(Status.REGISTERED);
        ResponseEntity<Policy> result = restTemplate
                .exchange(
                        "http://localhost:8080/policy/LV19-07-100000-1",
                        HttpMethod.PUT,
                        new HttpEntity<>(newTestPolicyForTest),
                        Policy.class
                );
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertNotSame(initTestPolicy(), result.getBody());
    }

    private Policy initTestPolicy() {
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

        policy.setUserName("Test user");
        policy.setPolicyStatus(Status.APPROVE);
        policy.setPolicyObjects(List.of(policyObject));

        return policy;
    }
}
