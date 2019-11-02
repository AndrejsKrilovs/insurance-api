package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;
import ru.krilovs.andrejs.insuranceapi.entity.Policy;

import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Service
public interface PolicyService {
    List<Policy> getAllPolicies();

    Policy getPolicy(String policy);

    Double calculatePremium(Policy policy);
}
