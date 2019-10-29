package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/policy")
public class PolicyAPI {
    private List<Map<String, Object>> data = new ArrayList<>();

    @Autowired
    private Validation validation;

    private Map<String, Object> getPolice(final String policy) {
        return data.stream()
                .filter(item -> String.valueOf(
                        ((Map<String, Object>)item.get(Properties.POLICY_PROPERTY)).get("id")
                ).equalsIgnoreCase(policy))
                .findFirst()
                .orElseThrow(PolicyNotFoundException::new);
    }

    private Map<String, Object> sortData(final Map<String, Object> unsortedMap) {
        return unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
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

        final Map<String, Object> sortedPolicy = sortData(policyBody);
        if(validation.policyStructureValidation(sortedPolicy)) {
            final Map<String, Object> sortedData = new LinkedHashMap<>() {{
                put(Properties.POLICY_PROPERTY, sortedPolicy);
            }};

            data.add(sortedData);
            return sortedData;
        } else
            return new LinkedHashMap<>(){{
                put("error", "Invalid policy!");
            }};
    }

    @PutMapping(path = "{policy}")
    public Map<String, Object> updatePolicy(@PathVariable String policy, @RequestBody Map<String, Object> policyBody) {
        final Map<String, Object> policyItem = getPolice(policy);
        policyBody.put("id", ((Map<String, Object>)policyItem.get(Properties.POLICY_PROPERTY)).get("id"));
        policyBody.put("premium", Math.random());

        final Map<String, Object> sortedPolicy = sortData(policyBody);
        if(validation.policyStructureValidation(sortedPolicy)) {
            final Map<String, Object> sortedData = new LinkedHashMap<>(){{
                put(Properties.POLICY_PROPERTY, sortedPolicy);
            }};

            policyItem.putAll(sortedData);
            return sortedData;
        } else
            return new LinkedHashMap<>(){{
                put("error", "Invalid policy!");
            }};
    }

    @DeleteMapping(path = "{policy}")
    public void deletePolicy(@PathVariable String policy) {
        final Map<String, Object> policyToDelete = getPolice(policy);
        data.remove(policyToDelete);
    }
}
