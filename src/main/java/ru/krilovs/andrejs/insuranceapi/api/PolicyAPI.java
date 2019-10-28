package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.Status;
import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping(path = "/policy")
public class PolicyAPI {
    private final static String POLICY_PROPERTY = "policy";
    private List<Map<String, Object>> data = new ArrayList<>();
    private Integer counter;

    @PostConstruct
    public void initializeData() {
        data.add(new TreeMap<>(){{put("user","Test user"); put("policy",new Policy("LV19-07-100000-0", Status.APPROVE, Math.random()));}});
        data.add(new TreeMap<>(){{put("user","Test user"); put("policy",new Policy("LV19-07-100000-1", Status.APPROVE, Math.random()));}});
        data.add(new TreeMap<>(){{put("user","Test user"); put("policy",new Policy("LV19-07-100000-2", Status.REGISTERED, Math.random()));}});
        counter = data.size();
    }

    private Map<String, Object> getPolice(@PathVariable String policy) {
        return data.stream()
                .filter(value -> ((Policy)value.get(POLICY_PROPERTY)).getId().equalsIgnoreCase(policy))
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
        final Policy policyObject = (Policy) policyItem.get(POLICY_PROPERTY);
        policyItem.putAll(policyBody);

        final String newStatusValue = String.valueOf(((Map) policyItem.get(POLICY_PROPERTY)).get("status"));
        policyObject.setStatus(Status.valueOf(newStatusValue));
        policyObject.setPremium(Math.random());
        policyItem.put(POLICY_PROPERTY, policyObject);

        return policyItem;
    }

    @DeleteMapping(path = "{policy}")
    public void deletePolicy(@PathVariable String policy) {
        data.remove(getPolice(policy));
    }
}
