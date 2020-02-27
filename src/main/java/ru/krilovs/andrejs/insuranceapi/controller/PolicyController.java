package ru.krilovs.andrejs.insuranceapi.controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.service.AbstractService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/policy")
@AllArgsConstructor
public class PolicyController {
    private final AbstractService<Policy> service;

    @GetMapping
    public List<Policy> showPolicyList() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Policy selectPolicyById(@PathVariable Long id) {
        return service.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public Policy addPolicy(@RequestBody Policy policy) {
        return service.add(policy);
    }

    @PutMapping(value = "/{id}")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return service.modify(id, policy);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePolicy(@PathVariable Long id) {
        service.delete(id);
    }
}
