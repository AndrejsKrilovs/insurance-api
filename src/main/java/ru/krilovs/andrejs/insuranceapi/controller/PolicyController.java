package ru.krilovs.andrejs.insuranceapi.controller;


import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.service.PolicyService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/policy")
public class PolicyController {
    private final PolicyService service;

    @GetMapping
    public List<Policy> getPolicyList() {
        return service.findAllPolicies();
    }

    @PostMapping
    public Policy addPolicy(@RequestBody Policy policy) {
        return service.addPolicy(policy);
    }

    @PutMapping(path = "{id}")
    public Policy modifyPolicy(@PathVariable(value = "id")Long id, @RequestBody Policy policyToModify) {
        return service.modifyPolicy(id, policyToModify);
    }

    @DeleteMapping(path = "{id}")
    public void deletePolicy(@PathVariable(value = "id")Long id) {
        service.deletePolicy(id);
    }
}
