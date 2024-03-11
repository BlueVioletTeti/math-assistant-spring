package com.geeksforless.mathassistantspring.service.impl;

import static java.nio.CharBuffer.wrap;

import com.geeksforless.mathassistantspring.exception.DataProcessingException;
import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import com.geeksforless.mathassistantspring.repository.EquationRepository;
import com.geeksforless.mathassistantspring.service.EquationService;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EquationServiceImpl implements EquationService {

    private final EquationRepository equationRepository;
    @Transactional
    @Override
    public Equation addEquation(Equation equation) {
        checkEquationCorrectness(equation);
        //TODO: check if already in the DB
        equationRepository.save(equation);
        return equation;
    }

    @Override
    public List<Equation> findAll() {
        return equationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Equation> findByRoot(Root root) {
        return equationRepository.findAllByRoot(root.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Equation> findByRootUniqueness() {
        return equationRepository.findAllByRootUniqueness();
    }

    private void checkEquationCorrectness(Equation equation) {
        String text = equation.getEquation();
        Pattern regex = Pattern.compile("[x+-/*()= ]|[0-9]");
        Matcher matcher = regex.matcher(wrap(text.toCharArray()));
        if (!matcher.find()) {
            throw new DataProcessingException("Equation is not valid. " +
                    "Please use only x to indicate unknown variable");
        }
        if(!areBracketsBalanced(text)) {
            throw new DataProcessingException("Equation is not valid. " +
                    "Please check use of brackets");
        }
        if(!areSignsCorrectlyPlaced(text)) {
            throw new DataProcessingException("Equation is not valid. " +
                    "Please check placement of arithmetic signs used");
        }

    }

    private Boolean areBracketsBalanced(String equation) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < equation.length(); i++) {
            char x = equation.charAt(i);
            if (x == '(' ) {
                stack.push(x);
            } else if (x == ')') {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private Boolean areSignsCorrectlyPlaced(String equation) {
        if (equation.startsWith("+")||
                equation.startsWith("*")||
                equation.startsWith("/")||
                equation.startsWith(")")||
                equation.endsWith("+")||
                equation.endsWith("-")||
                equation.endsWith("*")||
                equation.endsWith("/")||
                equation.endsWith("(")
        ) {
            return  false;
        }
        char[] charArray = equation.toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            if ((charArray[i] == '+'||
                    charArray[i] == '-'||
                    charArray[i] =='*'||
                    charArray[i] == '/'||
                    charArray[i] =='(')&&
                    (charArray[i + 1] == '+'||
                    charArray[i + 1] =='*'||
                    charArray[i + 1] == '/'||
                    charArray[i + 1] ==')')
                ) {
                return false;
            }
        }
        return true;
    }
}
