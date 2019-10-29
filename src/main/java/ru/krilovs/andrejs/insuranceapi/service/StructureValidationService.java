package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;
import ru.krilovs.andrejs.insuranceapi.api.Properties;
import ru.krilovs.andrejs.insuranceapi.exception.InvalidPoliceStructureException;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StructureValidationService {
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
        final List<Map<String, Object>> policySubObjects = policyObjects
                .stream()
                .map(item -> item.get("policySubObjects"))
                .map(item -> (List<Map<String, Object>>)item)
                .findFirst()
                .orElseThrow(InvalidPoliceStructureException::new);

        return keyValidation(policy.keySet(), Properties.ORIGINAL_POLICY_KEYS) &&
                hasSubObjects(policyObjects, Properties.ORIGINAL_POLICY_OBJECT_KEYS) &&
                hasSubObjects(policySubObjects, Properties.ORIGINAL_POLICY_SUB_OBJECT_KEYS);
    }
}
