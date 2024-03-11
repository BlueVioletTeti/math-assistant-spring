package com.geeksforless.mathassistantspring.controller;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import com.geeksforless.mathassistantspring.service.RootService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/roots")
public class RootController {
    private final RootService rootService;

    @PostMapping
    public Root save(@RequestBody @Valid Equation equation,
                     @RequestBody @Valid Root root) {
        return rootService.addRoot(equation, root);
    }
}
