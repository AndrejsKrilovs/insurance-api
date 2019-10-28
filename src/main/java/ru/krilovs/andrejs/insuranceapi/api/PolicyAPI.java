package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;

import javax.annotation.PostConstruct;

import java.util.*;
import java.util.stream.Collectors;

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

    private Map<String, Object> getPolice(final String policy) {
        return data.stream()
                .filter(item -> String.valueOf(
                        ((Map<String, Object>)item.get(POLICY_PROPERTY)).get("id")
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
        policyBody.put("id", String.format("LV19-07-100000-%d", counter++));
        policyBody.put("premium", Math.random());

        final Map<String, Object> sortedData = new LinkedHashMap<>(){{
            put(POLICY_PROPERTY, sortData(policyBody));
        }};

        data.add(sortedData);
        return sortedData;
    }

    @PutMapping(path = "{policy}")
    public Map<String, Object> updatePolicy(@PathVariable String policy, @RequestBody Map<String, Object> policyBody) {
        final Map<String, Object> policyItem = getPolice(policy);
        policyBody.put("id", ((Map<String, Object>)policyItem.get(POLICY_PROPERTY)).get("id"));
        policyBody.put("premium", Math.random());

        final Map<String, Object> sortedData = new LinkedHashMap<>(){{
            put(POLICY_PROPERTY, sortData(policyBody));
        }};

        policyItem.putAll(sortedData);
        return sortedData;
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
