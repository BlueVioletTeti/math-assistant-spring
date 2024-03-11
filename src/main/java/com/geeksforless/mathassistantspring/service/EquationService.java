package com.geeksforless.mathassistantspring.service;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import java.util.List;

public interface EquationService {
    Equation addEquation(Equation equation);

    List<Equation> findAll();

    List<Equation> findByRoot(Root root);

    List<Equation> findByRootUniqueness();
}
