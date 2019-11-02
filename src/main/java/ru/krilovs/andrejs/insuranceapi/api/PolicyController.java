package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.service.PolicyService;
import ru.krilovs.andrejs.insuranceapi.validators.PolicyValidator;

import java.util.List;

/**
 * @author Andrejs Krilovs
 * @author Sergei Visotsky
 */
@RestController
@RequestMapping(value = "/policy")
public class PolicyController {
    private final PolicyValidator policyValidator;
    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyValidator policyValidator, PolicyService policyService) {
        this.policyValidator = policyValidator;
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity<List<Policy>> policyList() {
        List<Policy> allPolicies = policyService.getAllPolicies();
        return new ResponseEntity<>(allPolicies, HttpStatus.OK);
    }

    @GetMapping(value = "{policy}")
    public ResponseEntity<Policy> showPolicy(@PathVariable String policy) {
        Policy policyFound = policyService.getPolicy(policy);
        if (policyFound != null) {
            return new ResponseEntity<>(policyFound, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policyBody) {
        List<Policy> allPolicies = policyService.getAllPolicies();
        Policy updatedPolicyPrimaryData = policyService.updatePolicyPrimaryData(
                policyBody,
                String.format("LV19-07-100000-%d", allPolicies.size() + 1),
                policyService.calculatePremium(policyBody)
        );
        if (policyValidator.validate(updatedPolicyPrimaryData)) {
            allPolicies.add(updatedPolicyPrimaryData);
            return new ResponseEntity<>(updatedPolicyPrimaryData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "{policy}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable String policy, @RequestBody Policy policyBody) {
        List<Policy> allPolicies = policyService.getAllPolicies();
        Policy policyItem = policyService.getPolicy(policy);
        Policy updatedPolicyPrimaryData = policyService.updatePolicyPrimaryData(
                policyBody,
                policyItem.getPolicyNumber(),
                policyService.calculatePremium(policyBody)
        );
        if (policyValidator.validate(updatedPolicyPrimaryData)) {
            allPolicies.set(allPolicies.indexOf(policyItem), updatedPolicyPrimaryData);
            return new ResponseEntity<>(updatedPolicyPrimaryData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "{policy}")
    public void deletePolicy(@PathVariable String policy) {
        List<Policy> policies = policyService.getAllPolicies();
        Policy policyFound = policyService.getPolicy(policy);
        policies.remove(policyFound);
    }
}
