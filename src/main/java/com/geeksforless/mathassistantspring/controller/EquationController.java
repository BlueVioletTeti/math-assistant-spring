package com.geeksforless.mathassistantspring.controller;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import com.geeksforless.mathassistantspring.service.EquationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/equations")
public class EquationController {
    private final EquationService equationService;

    @PostMapping
    public Equation save(@RequestBody @Valid Equation equation) {
        return equationService.addEquation(equation);
    }

    @GetMapping
    public List<Equation> getAll() {
        return equationService.findAll();
    }

    @GetMapping("root")
    public List<Equation> getAllByRoot(@RequestParam Root root) {
        return equationService.findByRoot(root);
    }

    @GetMapping("/one_root")
    public List<Equation> getAllByRootUniqueness() {
        return equationService.findByRootUniqueness();
    }
}
