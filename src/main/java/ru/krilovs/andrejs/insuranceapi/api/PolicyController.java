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
        // TODO: @svisotsky to @akrilovs: Move this logic to the service classes
        List<Policy> allPolicies = policyService.getAllPolicies();
        policyBody.setPolicyNumber(String.format("LV19-07-100000-%d", allPolicies.size() + 1));

        // TODO: @svisotsky to @akrilovs: Move this logic to the service classes
        Double policyPremium = policyService.calculatePremium(policyBody);
        policyBody.setPolicyPremium(policyPremium);

        if (policyValidator.validate(policyBody)) {
            allPolicies.add(policyBody);
            return new ResponseEntity<>(policyBody, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "{policy}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable String policy, @RequestBody Policy policyBody) {

        // TODO: @svisotsky to @akrilovs: Move this logic to the service classes
        List<Policy> allPolicies = policyService.getAllPolicies();

        // TODO: @svisotsky to @akrilovs: Move this logic to the service classes
        Policy policyItem = policyService.getPolicy(policy);
        policyBody.setPolicyNumber(policyItem.getPolicyNumber());
        // TODO: @svisotsky to @akrilovs: Move this logic to the service classes
        Double policyPremium = policyService.calculatePremium(policyBody);
        policyBody.setPolicyPremium(policyPremium);

        if (policyValidator.validate(policyBody)) {
            allPolicies.set(allPolicies.indexOf(policyItem), policyBody);
            return new ResponseEntity<>(policyBody, HttpStatus.OK);
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
