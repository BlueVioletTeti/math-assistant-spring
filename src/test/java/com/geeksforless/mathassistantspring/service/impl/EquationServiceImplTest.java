package com.geeksforless.mathassistantspring.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import com.geeksforless.mathassistantspring.repository.EquationRepository;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EquationServiceImplTest {
    private static final Long DEFAULT_ID_1 = 1L;
    private static final Long DEFAULT_ID_2 = 2L;
    private static final Long DEFAULT_ID_3 = 3L;
    private static final Long DEFAULT_ID_4 = 4L;
    private static final Long DEFAULT_ID_5 = 5L;

    private static final String DEFAULT_EQUATION_1 = "2*x+5=17";
    private static final String DEFAULT_EQUATION_2 = "-1.3*5/x=1.2";
    private static final String DEFAULT_EQUATION_3 = "2*x*x=10";
    private static final String DEFAULT_EQUATION_4 = "2*(x+5+x)+5=10";
    private static final String DEFAULT_EQUATION_5 = "17=2*x+5";

    private static final double DEFAULT_ROOT_1 = 6d;
    private static final double DEFAULT_ROOT_2 = -5.4166666666666666d;
    private static final double DEFAULT_ROOT_3 = -Math.sqrt(5);
    private static final double DEFAULT_ROOT_4 = Math.sqrt(5);
    private static final double DEFAULT_ROOT_5 = -1.25d;

    @Mock
    private EquationRepository equationRepository;

    @InjectMocks
    private EquationServiceImpl equationService;

    @Test
    void addEquation_ValidData_ReturnsEquation() {
        Equation actualEquation1 = getEquation1();
        Equation actualEquation2 = getEquation2();
        Equation actualEquation3 = getEquation3();
        Equation actualEquation4 = getEquation4();
        Equation actualEquation5 = getEquation5();

        when(equationRepository.save(actualEquation1)).thenReturn(actualEquation1);
        when(equationRepository.save(actualEquation2)).thenReturn(actualEquation2);
        when(equationRepository.save(actualEquation3)).thenReturn(actualEquation3);
        when(equationRepository.save(actualEquation4)).thenReturn(actualEquation4);
        when(equationRepository.save(actualEquation5)).thenReturn(actualEquation5);

        Equation expectedEquation1 = equationService.addEquation(actualEquation1);
        Equation expectedEquation2 = equationService.addEquation(actualEquation2);
        Equation expectedEquation3 = equationService.addEquation(actualEquation3);
        Equation expectedEquation4 = equationService.addEquation(actualEquation4);
        Equation expectedEquation5 = equationService.addEquation(actualEquation5);

        assertThat(actualEquation1).isEqualTo(expectedEquation1);
        assertThat(actualEquation2).isEqualTo(expectedEquation2);
        assertThat(actualEquation3).isEqualTo(expectedEquation3);
        assertThat(actualEquation4).isEqualTo(expectedEquation4);
        assertThat(actualEquation5).isEqualTo(expectedEquation5);

        verifyNoMoreInteractions(equationRepository);
    }

    @Test
    void findAll_ValidData_ReturnsListOfEquations() {
        Equation actualEquation1 = getEquation1();
        Equation actualEquation2 = getEquation2();
        List<Equation> actual = List.of(actualEquation1, actualEquation2);

        when(equationRepository.findAll()).thenReturn(actual);

        List<Equation> expected = equationService.findAll();

        assertThat(actual).isEqualTo(expected);

        verifyNoMoreInteractions(equationRepository);
    }

    @Test
    void findByRoot_ValidData_ReturnsListOfEquations() { //TODO: double check logic
        Equation actualEquation1 = getEquation1();
        Equation actualEquation2 = getEquation2();
        Equation actualEquation3 = getEquation3();
        Equation actualEquation4 = getEquation4();
        Equation actualEquation5 = getEquation5();

        Root actualRoot = actualEquation1.getRoots()
                .stream()
                .toList()
                .get(0);

        List<Equation> actual = List.of(actualEquation1, actualEquation5);

        when(equationRepository.findAllByRoot(actualRoot.getId())).thenReturn(actual);

        List<Equation> expected = equationService.findByRoot(actualRoot);

        assertThat(actual).isEqualTo(expected);

        verifyNoMoreInteractions(equationRepository);
    }

    @Test
    void findByRootUniqueness_ValidData_ReturnsListOfEquations() {
        Equation actualEquation1 = getEquation1();
        Equation actualEquation2 = getEquation2();
        Equation actualEquation3 = getEquation3();
        Equation actualEquation4 = getEquation4();
        Equation actualEquation5 = getEquation5();

        List<Equation> actual = List.of(actualEquation1, actualEquation2,
                actualEquation4, actualEquation5);

        when(equationRepository.findAllByRootUniqueness()).thenReturn(actual);

        List<Equation> expected = equationService.findByRootUniqueness();

        assertThat(actual).isEqualTo(expected);

        verifyNoMoreInteractions(equationRepository);
    }

    private static Equation getEquation1() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_1)
                .setId(DEFAULT_ID_1)
                .setRoots(Set.of(new Root()
                        .setRoot(DEFAULT_ROOT_1)
                        .setId(DEFAULT_ID_1)));
    }

    private static Equation getEquation2() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_2)
                .setId(DEFAULT_ID_2)
                .setRoots(Set.of(new Root()
                        .setRoot(DEFAULT_ROOT_2)
                        .setId(DEFAULT_ID_2)));
    }

    private static Equation getEquation3() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_3)
                .setId(DEFAULT_ID_3)
                .setRoots(Set.of(
                        new Root()
                                .setRoot(DEFAULT_ROOT_3)
                                .setId(DEFAULT_ID_3),
                        new Root()
                                .setRoot(DEFAULT_ROOT_4)
                                .setId(DEFAULT_ID_4)));
    }

    private static Equation getEquation4() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_4)
                .setId(DEFAULT_ID_4)
                .setRoots(Set.of(new Root()
                        .setRoot(DEFAULT_ROOT_5)
                        .setId(DEFAULT_ID_5)));
    }

    private static Equation getEquation5() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_5)
                .setId(DEFAULT_ID_5)
                .setRoots(Set.of(new Root()
                        .setRoot(DEFAULT_ROOT_1)
                        .setId(DEFAULT_ID_1)));
    }
}
