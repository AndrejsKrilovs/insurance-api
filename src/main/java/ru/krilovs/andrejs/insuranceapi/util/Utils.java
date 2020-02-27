package ru.krilovs.andrejs.insuranceapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.PolicyObject;
import ru.krilovs.andrejs.insuranceapi.entity.PolicySubObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
    public static void copyPolicyProperties(Policy source, Policy target) {
        BeanUtils.copyProperties(source, target, "id");
        copyPolicyObjectProperties(source, target);
        copyPolicySubObjectProperties(source, target);
    }

    private static void copyPolicyObjectProperties(Policy source, Policy target) {
        List<PolicyObject> sourceObjects = new ArrayList<>(source.getPolicyObjects());
        List<PolicyObject> targetObjects = new ArrayList<>(target.getPolicyObjects());

        IntStream.range(0, sourceObjects.size())
                .forEach(index ->
                        BeanUtils.copyProperties(sourceObjects.get(index),targetObjects.get(index),"id")
                );
    }

    private static void copyPolicySubObjectProperties(Policy source, Policy target) {
        List<PolicySubObject> sourceSubObjects = source.getPolicyObjects().stream()
                .flatMap(item -> item.getPolicySubObjects().stream())
                .collect(Collectors.toList());

        List<PolicySubObject> targetSubObjects = target.getPolicyObjects().stream()
                .flatMap(item -> item.getPolicySubObjects().stream())
                .collect(Collectors.toList());

        IntStream.range(0, sourceSubObjects.size())
                .forEach(index ->
                        BeanUtils.copyProperties(sourceSubObjects.get(index),targetSubObjects.get(index),"id")
                );
    }
}
