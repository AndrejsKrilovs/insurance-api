package ru.krilovs.andrejs.insuranceapi.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import ru.krilovs.andrejs.insuranceapi.entity.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PolicyServiceTest {
    private final RestTemplate restTemplate = new RestTemplate();
    private Policy policy;
    private PolicyObject policyObject;
    private PolicySubObject policySubObject1;
    private PolicySubObject policySubObject2;

    @Before
    public void init() {
        policy = new Policy();
        policyObject = new PolicyObject();
        policySubObject1 = new PolicySubObject();
        policySubObject2 = new PolicySubObject();

        policySubObject1.setName("Sub object 1");
        policySubObject1.setInsuredSum(Double.valueOf(45.01));
        policySubObject1.setRiskType(RiskType.FIRE);

        policySubObject2.setName("Sub object 1");
        policySubObject2.setInsuredSum(Double.valueOf(54.99));
        policySubObject2.setRiskType(RiskType.WATER);

        policyObject.setName("Object");
        policyObject.setSubObjects(List.of(policySubObject1, policySubObject2));

        policy.setUserName("Test user");
        policy.setPolicyStatus(Status.APPROVE);
        policy.setPolicyObjects(List.of(policyObject));
    }

    @Test
    public void postPolicyTest() {
        final ResponseEntity<String> result = restTemplate
                .postForEntity("http://localhost:8080/policy", policy, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void putPoliceTest() {
        policy.setUserName("Changed user");
        policySubObject2.setInsuredSum(Double.valueOf(100));
        policySubObject1.setRiskType(RiskType.FIRE);
        final ResponseEntity<String> result = restTemplate
                .exchange(
                        "http://localhost:8080/policy/LV19-07-100000-1",
                        HttpMethod.PUT,
                        new HttpEntity<>(policy),
                        String.class
                );
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getAllPoliciesTest() {
        final ResponseEntity<Policy[]> result = restTemplate
                .getForEntity("http://localhost:8080/policy", Policy[].class);
        assertTrue(result.getBody().length > 0);
    }

    @Test
    public void deleteCurrentPolicy() {
        final ResponseEntity<String> result = restTemplate
                .exchange(
                        "http://localhost:8080/policy/LV19-07-100000-1",
                        HttpMethod.DELETE,
                        new HttpEntity<>(policy),
                        String.class
                );
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
