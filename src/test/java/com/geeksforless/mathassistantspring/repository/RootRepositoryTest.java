package com.geeksforless.mathassistantspring.repository;

import com.geeksforless.mathassistantspring.model.Root;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RootRepositoryTest {
    private static final Long DEFAULT_ID_1 = 1L;
    private static final Long DEFAULT_ID_2 = 2L;
    private static final Long DEFAULT_ID_3 = 3L;
    private static final Long DEFAULT_ID_4 = 4L;
    private static final Long DEFAULT_ID_5 = 5L;

    private static final double DEFAULT_ROOT_1 = 6d;
    private static final double DEFAULT_ROOT_2 = -5.4166666666666666d;
    private static final double DEFAULT_ROOT_3 = -Math.sqrt(5);
    private static final double DEFAULT_ROOT_4 = Math.sqrt(5);
    private static final double DEFAULT_ROOT_5 = -1.25d;


    @Autowired
    private RootRepository rootRepository;

    @Test
    @Sql(scripts = {"classpath:database/equations/add-default-equations-and-roots.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/equations/remove-all-from-all-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAll() {
        List<Root> expected = new ArrayList<>();
        expected.add(getRoot1());
        expected.add(getRoot2());
        expected.add(getRoot3());
        expected.add(getRoot4());
        expected.add(getRoot5());

        List<Root> actual = rootRepository.findAll();
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0), actual.get(0));
        Assertions.assertEquals(expected.get(2), actual.get(2));
    }

    private static Root getRoot1() {
        return new Root()
                        .setRoot(DEFAULT_ROOT_1)
                        .setId(DEFAULT_ID_1);
    }

    private static Root getRoot2() {
        return new Root()
                        .setRoot(DEFAULT_ROOT_2)
                        .setId(DEFAULT_ID_2);
    }

    private static Root getRoot3() {
        return new Root()
                .setRoot(DEFAULT_ROOT_3)
                .setId(DEFAULT_ID_3);
    }

    private static Root getRoot4() {
        return new Root()
                .setRoot(DEFAULT_ROOT_4)
                .setId(DEFAULT_ID_4);
    }

    private static Root getRoot5() {
        return new Root()
               .setRoot(DEFAULT_ROOT_5)
               .setId(DEFAULT_ID_5);
    }
}
