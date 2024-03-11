package com.geeksforless.mathassistantspring.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import com.geeksforless.mathassistantspring.repository.EquationRepository;
import com.geeksforless.mathassistantspring.repository.RootRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RootServiceImplTest {
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
    private RootRepository rootRepository;
    @Mock
    private EquationRepository equationRepository;

    @InjectMocks
    private RootServiceImpl rootService;

    @Test
    void addRoot_ValidData_ReturnsRoot() {
        Root actualRoot1 = new Root()
                .setRoot(DEFAULT_ROOT_1)
                .setId(DEFAULT_ID_1);

        Root actualRoot2= new Root()
                .setRoot(DEFAULT_ROOT_2)
                .setId(DEFAULT_ID_2);

        Root actualRoot3 = new Root()
                .setRoot(DEFAULT_ROOT_3)
                .setId(DEFAULT_ID_3);

        Root actualRoot4 = new Root()
                .setRoot(DEFAULT_ROOT_4)
                .setId(DEFAULT_ID_4);

        Root actualRoot5 = new Root()
                .setRoot(DEFAULT_ROOT_5)
                .setId(DEFAULT_ID_5);

        when(rootRepository.save(actualRoot1)).thenReturn(actualRoot1);
        when(rootRepository.save(actualRoot2)).thenReturn(actualRoot2);
        when(rootRepository.save(actualRoot3)).thenReturn(actualRoot3);
        when(rootRepository.save(actualRoot4)).thenReturn(actualRoot4);
        when(rootRepository.save(actualRoot5)).thenReturn(actualRoot5);

        when(equationRepository.save(getEquation1())).thenReturn(getEquation1());
        when(equationRepository.save(getEquation2())).thenReturn(getEquation2());
        when(equationRepository.save(getEquation3())).thenReturn(getEquation3());
        when(equationRepository.save(getEquation4())).thenReturn(getEquation4());
        when(equationRepository.save(getEquation5())).thenReturn(getEquation5());

        Root expectedRoot1a = rootService.addRoot(getEquation1(), actualRoot1);
        Root expectedRoot1b = rootService.addRoot(getEquation5(), actualRoot1);
        Root expectedRoot2 = rootService.addRoot(getEquation2(), actualRoot2);
        Root expectedRoot3 = rootService.addRoot(getEquation3(), actualRoot3);
        Root expectedRoot4 = rootService.addRoot(getEquation3(), actualRoot4);
        Root expectedRoot5 = rootService.addRoot(getEquation4(), actualRoot5);

        assertThat(actualRoot1).isEqualTo(expectedRoot1a);
        assertThat(actualRoot1).isEqualTo(expectedRoot1b);
        assertThat(actualRoot2).isEqualTo(expectedRoot2);
        assertThat(actualRoot3).isEqualTo(expectedRoot3);
        assertThat(actualRoot4).isEqualTo(expectedRoot4);
        assertThat(actualRoot5).isEqualTo(expectedRoot5);

        verifyNoMoreInteractions(rootRepository, equationRepository);
    }

    private static Equation getEquation1() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_1)
                .setId(DEFAULT_ID_1);
    }

    private static Equation getEquation2() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_2)
                .setId(DEFAULT_ID_2);
    }

    private static Equation getEquation3() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_3)
                .setId(DEFAULT_ID_3);
    }

    private static Equation getEquation4() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_4)
                .setId(DEFAULT_ID_4);
    }

    private static Equation getEquation5() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_5)
                .setId(DEFAULT_ID_5);
    }
}
