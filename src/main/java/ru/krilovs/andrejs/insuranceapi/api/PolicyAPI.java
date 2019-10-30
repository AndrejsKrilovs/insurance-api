package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;
import ru.krilovs.andrejs.insuranceapi.service.DataValidationService;
import ru.krilovs.andrejs.insuranceapi.service.StructureValidationService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/policy")
public class PolicyAPI {
    private List<Map<String, Object>> data = new ArrayList<>();

    @Autowired
    private StructureValidationService structureValidationService;

    @Autowired
    private DataValidationService dataValidation;

    private Map<String, Object> getPolice(final String policy) {
        return data.stream()
                .filter(item -> String.valueOf(
                        ((Map<String, Object>)item.get(Properties.POLICY_PROPERTY)).get("id")
                ).equalsIgnoreCase(policy))
                .findFirst()
                .orElseThrow(PolicyNotFoundException::new);
    }

    @GetMapping
    public List<Map<String, Object>> policyList() {
        return data;
    }

    @GetMapping(path = "{policy}")
    public Map<String, Object> showPolicy(@PathVariable String policy) {
        return getPolice(policy);
    }

    @PostMapping
    public Map<String, Object> createPolicy(@RequestBody Map<String, Object> policyBody) {
        policyBody.put("id", String.format("LV19-07-100000-%d", data.size()+1));
        policyBody.put("premium", Math.random());

        final Map<String, Object> sortedData = new LinkedHashMap<>() {{
            put(Properties.POLICY_PROPERTY, structureValidationService.generatePolicyStructure(policyBody));
        }};

        data.add(sortedData);
        return sortedData;
    }

    @PutMapping(path = "{policy}")
    public Map<String, Object> updatePolicy(@PathVariable String policy, @RequestBody Map<String, Object> policyBody) {
        final Map<String, Object> policyItem = getPolice(policy);
        policyBody.put("id", ((Map<String, Object>)policyItem.get(Properties.POLICY_PROPERTY)).get("id"));
        policyBody.put("premium", Math.random());

        final Map<String, Object> sortedData = new LinkedHashMap<>(){{
            put(Properties.POLICY_PROPERTY, structureValidationService.generatePolicyStructure(policyBody));
        }};

        policyItem.putAll(sortedData);
        return sortedData;
    }

    @DeleteMapping(path = "{policy}")
    public void deletePolicy(@PathVariable String policy) {
        final Map<String, Object> policyToDelete = getPolice(policy);
        data.remove(policyToDelete);
    }
}