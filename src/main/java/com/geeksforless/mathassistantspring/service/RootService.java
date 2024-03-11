package com.geeksforless.mathassistantspring.service;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;

public interface RootService {
    Root addRoot(Equation equation, Root root);
}
