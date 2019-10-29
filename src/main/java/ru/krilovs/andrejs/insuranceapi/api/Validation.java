package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class Validation {
    private boolean keyValidation(final Set<String> keysToCompare, final Set<String> originalKeys) {
        return keysToCompare.equals(originalKeys);
    }

    private boolean hasSubObjects(final List<Map<String, Object>> arrayToCheck, final Set<String> originalKeys) {
        return arrayToCheck.size() > 0 && arrayToCheck.stream()
                .map(Map::keySet)
                .allMatch(item -> item.equals(originalKeys));
    }

    public boolean policyStructureValidation(final Map<String, Object> policy) {
        final List<Map<String, Object>> policyObjects = (List<Map<String, Object>>) policy.get("policyObjects");
        System.out.println(policyObjects);
        return keyValidation(policy.keySet(), Properties.ORIGINAL_POLICY_KEYS) &&
                hasSubObjects(policyObjects, Properties.ORIGINAL_POLICY_OBJECT_KEYS);
    }
}
