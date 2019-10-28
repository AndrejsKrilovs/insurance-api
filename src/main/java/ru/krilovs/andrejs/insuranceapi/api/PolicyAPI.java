package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/policy")
public class PolicyAPI {
    private final static String POLICY_PROPERTY = "policy";
    private List<Map<String, Object>> data = new ArrayList<>();
    private Integer counter;

    @PostConstruct
    public void initializeData() {
           counter = data.size();
    }

    private Map<String, Object> getPolice(@PathVariable String policy) {
        return data.stream().map(v -> ((Map) v.get(POLICY_PROPERTY)))
                .filter(item -> String.valueOf(item.get("id")).equalsIgnoreCase(policy))
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
       final Map<String, Object> item = (Map) policyBody.get(POLICY_PROPERTY);

       item.put("id", String.format("LV19-07-100000-%d", counter++));
       item.put("premium", Math.random());
       data.add(policyBody);

       return policyBody;
    }

    @PutMapping(path = "{policy}")
    public Map<String, Object> updatePolicy(@PathVariable String policy, @RequestBody Map<String, Object> policyBody) {
        final Map<String, Object> policyItem = getPolice(policy);
        final Map<String, Object> newPolicyItem = (Map) policyBody.get(POLICY_PROPERTY);

        policyItem.putAll(newPolicyItem);
        policyItem.put("id", policyItem.get("id"));
        policyItem.put("premium", Math.random());

        return policyItem;
    }

    @DeleteMapping(path = "{policy}")
    public void deletePolicy(@PathVariable String policy) {
        final Map<String, Object> policyToDelete = getPolice(policy);
        data.remove(
                data.stream()
                        .filter(item -> item.get(POLICY_PROPERTY).equals(policyToDelete))
                        .findFirst()
                        .orElseThrow(PolicyNotFoundException::new)
        );
    }
}
