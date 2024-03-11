package com.geeksforless.mathassistantspring.repository;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EquationRepositoryTest {
    private static final Long DEFAULT_ID_1 = 1L;
    private static final Long DEFAULT_ID_2 = 2L;
    private static final Long DEFAULT_ID_3 = 3L;
    private static final Long DEFAULT_ID_4 = 4L;
    private static final Long DEFAULT_ID_5 = 5L;

    private static final String DEFAULT_EQUATION_1 = "2*x+5=17";
    private static final String DEFAULT_EQUATION_2 = "-1.3*5/x=1.2";
    private static final String DEFAULT_EQUATION_3 = "2*x*x=10";
    private static final String DEFAULT_EQUATION_4 = "2*(x+5+х)+5=10";
    private static final String DEFAULT_EQUATION_5 = "17=2*x+5";

    private static final double DEFAULT_ROOT_1 = 6d;
    private static final double DEFAULT_ROOT_2 = -5.4166666666666666d;
    private static final double DEFAULT_ROOT_3 = -Math.sqrt(5);
    private static final double DEFAULT_ROOT_4 = Math.sqrt(5);
    private static final double DEFAULT_ROOT_5 = -1.25d;

    @Autowired
    private EquationRepository equationRepository;

    @Test
    @Sql(scripts = {"classpath:database/equations/add-default-equations-and-roots.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/equations/remove-all-from-all-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAll() {
        List<Equation> expected = new ArrayList<>();
        expected.add(getEquation1());
        expected.add(getEquation2());
        expected.add(getEquation3());
        expected.add(getEquation4());
        expected.add(getEquation5());

        List<Equation> actual = equationRepository.findAll();
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0), actual.get(0));
        Assertions.assertEquals(expected.get(2), actual.get(2));
    }

    @Test
    @Sql(scripts = {"classpath:database/equations/add-default-equations-and-roots.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/equations/remove-all-from-all-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllByRoot() {
        List<Equation> actual1 = equationRepository.findAllByRoot(getEquation1().getId());
        List<Equation> actual2 = equationRepository.findAllByRoot(getEquation2().getId());
        Assertions.assertEquals(2, actual1.size());
        Assertions.assertEquals(1, actual2.size());
    }

    @Test
    @Sql(scripts = {"classpath:database/equations/add-default-equations-and-roots.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/equations/remove-all-from-all-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllByRootUniqueness() {
        List<Equation> actual = equationRepository.findAllByRootUniqueness();
        Assertions.assertEquals(4, actual.size());
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
