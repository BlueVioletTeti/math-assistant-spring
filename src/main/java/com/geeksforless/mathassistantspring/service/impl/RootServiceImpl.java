package com.geeksforless.mathassistantspring.service.impl;

import com.geeksforless.mathassistantspring.exception.DataProcessingException;
import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import com.geeksforless.mathassistantspring.repository.EquationRepository;
import com.geeksforless.mathassistantspring.repository.RootRepository;
import com.geeksforless.mathassistantspring.service.RootService;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RootServiceImpl implements RootService {
    private final RootRepository rootRepository;
    private final EquationRepository equationRepository;

    @Transactional
    @Override
    public Root addRoot(Equation equation, Root root) {
        String equationText = equation.getEquation();
        Double rootDouble = root.getRoot();
        if (!isValid(equationText, rootDouble)) {
            throw new DataProcessingException("Root is not valid. Review your calculations");
        }
        rootRepository.save(root);
        //TODO: check if already in the DB
        equationRepository.save(equation);
        return root;
    }

    private Boolean isValid(String equation, Double root) {
        String[] sides = equation.replaceAll("x", root.toString())
                .split("=");
        String lhs = sides[0];
        String rhs = sides[1];

        Expression expressionLhs = new ExpressionBuilder(lhs).build();
        double resultLhs = expressionLhs.evaluate();

        Expression expressionRhs = new ExpressionBuilder(rhs).build();
        double resultRhs = expressionRhs.evaluate();

        double diff = resultLhs - resultRhs;

        return Math.abs(diff) < Math.pow(10, -9);
    }
}
