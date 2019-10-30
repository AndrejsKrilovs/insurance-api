package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;

import ru.krilovs.andrejs.insuranceapi.api.Properties;
import ru.krilovs.andrejs.insuranceapi.exception.InvalidPoliceStructureException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Map<String, Object> generatePolicyStructure(final Map<String, Object> policy)
            throws InvalidPoliceStructureException{

        final List<Map<String, Object>> policyObjects = (List<Map<String, Object>>) policy.get("policyObjects");
        final List<Map<String, Object>> policySubObjects = policyObjects
                .stream()
                .map(item -> item.get("policySubObjects"))
                .map(item -> (List<Map<String, Object>>)item)
                .findFirst()
                .orElseThrow(InvalidPoliceStructureException::new);

        if(keyValidation(policy.keySet(), Properties.ORIGINAL_POLICY_KEYS) &&
                hasSubObjects(policyObjects, Properties.ORIGINAL_POLICY_OBJECT_KEYS) &&
                hasSubObjects(policySubObjects, Properties.ORIGINAL_POLICY_SUB_OBJECT_KEYS)) {

            return sortData(policy);
        } else
            throw new InvalidPoliceStructureException();
    }
}
