package ru.krilovs.andrejs.insuranceapi.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.PolicyObject;
import ru.krilovs.andrejs.insuranceapi.entity.PolicySubObject;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {
    public static void validatePolicy(Policy policy) {
        Preconditions.checkNotNull(policy);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(policy.getName()));
        policy.getPolicyObjects().forEach(Validator::validatePolicyObject);
        policy.getPolicyObjects().stream()
                .flatMap(item -> item.getPolicySubObjects().stream())
                .forEach(Validator::validatePolicySubObject);
    }

    private static void validatePolicyObject(PolicyObject object) {
        Preconditions.checkNotNull(object);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(object.getName()));
    }

    private static void validatePolicySubObject(PolicySubObject subObject) {
        Preconditions.checkNotNull(subObject);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(subObject.getName()));
        Preconditions.checkNotNull(subObject.getRiskType());
        Preconditions.checkNotNull(subObject.getInsuredAmount());
        Preconditions.checkArgument(subObject.getInsuredAmount().signum() > 0);
    }
}
