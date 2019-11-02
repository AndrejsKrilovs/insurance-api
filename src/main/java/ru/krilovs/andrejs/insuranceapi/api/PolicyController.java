package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.exception.InvalidPolicyDataException;
import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/policy")
public class PolicyController {
    private List<Policy> data = new ArrayList<>();

    @Autowired
    private ValidationService validation;

    public Double calculatePremium(final Policy policy) {
        return policy.getPolicyObjects().stream()
                .map(item -> item.getSubObjects())
                .findAny()
                .get()
                .stream()
                .map(item -> item.getInsuredSum())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    private Policy getPolicy(final String policy) {
        return data.stream()
                .filter(item -> item.getPolicyNumber().equalsIgnoreCase(policy))
                .findFirst()
                .orElseThrow(PolicyNotFoundException::new);
    }

    @GetMapping
    public List<Policy> policyList() {
        return data;
    }

    @GetMapping(path = "{policy}")
    public Policy showPolicy(@PathVariable String policy) {
        return getPolicy(policy);
    }

    @PostMapping
    public Policy createPolicy(@RequestBody Policy policyBody) throws InvalidPolicyDataException {
        policyBody.setPolicyNumber(String.format("LV19-07-100000-%d", data.size()+1));
        policyBody.setPolicyPremium(calculatePremium(policyBody));

        if(validation.validatePolice(policyBody)) {
            data.add(policyBody);
            return policyBody;
        } else
            throw new InvalidPolicyDataException();
    }

    @PutMapping(path = "{policy}")
    public Policy updatePolicy(@PathVariable String policy, @RequestBody Policy policyBody)
            throws InvalidPolicyDataException {

        final Policy policyItem = getPolicy(policy);
        policyBody.setPolicyNumber(policyItem.getPolicyNumber());
        policyBody.setPolicyPremium(calculatePremium(policyBody));

        if(validation.validatePolice(policyBody)) {
            data.set(data.indexOf(policyItem), policyBody);
            return policyBody;
        } else
            throw new InvalidPolicyDataException();
    }

    @DeleteMapping(path = "{policy}")
    public void deletePolicy(@PathVariable String policy) {
        data.remove(getPolicy(policy));
    }
}
