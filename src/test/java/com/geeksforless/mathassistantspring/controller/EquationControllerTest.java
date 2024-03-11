package com.geeksforless.mathassistantspring.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EquationControllerTest {
    protected static MockMvc mockMvc;

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
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext applicationContext
    ) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    @Sql(scripts = "classpath:database/equations/remove-all-from-all-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void save_ValidEquation_Success() throws Exception {
        Equation expected = new Equation()
                .setEquation(DEFAULT_EQUATION_1)
                .setId(DEFAULT_ID_1);

        String jsonRequest = objectMapper.writeValueAsString(expected);
        MvcResult result = mockMvc.perform(
                        post("/api/equations")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Equation actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                Equation.class);
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getEquation(), actual.getEquation());
        EqualsBuilder.reflectionEquals(expected, actual, "id");
    }

    @Test
    @Sql(scripts = {"classpath:database/equations/add-default-equations-and-roots.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/equations/remove-all-from-all-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getAll_NonEmptyRepository_ReturnsListOfAllEquations() throws Exception {
        //TODO: fix test
//        List<Equation> expected = new ArrayList<>();
//        expected.add(getEquation1());
//        expected.add(getEquation2());
//        expected.add(getEquation3());
//        expected.add(getEquation4());
//        expected.add(getEquation5());
//
//        MvcResult result = mockMvc.perform(
//                get("/api/equations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Equation[] actual = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
//                Equation[].class);
//        assertEquals(5, actual.length);
//        EqualsBuilder.reflectionEquals(expected, Arrays.stream(actual).toList(), "roots");
    }

    @Test
    void getAllByRoot_ValidRoot_ReturnsListOfEquations() {
        //TODO: write test
    }

    @Test
    void getAllByRootUniqueness_NonEmptyRepository_ReturnsListOfEquations() {
        //TODO: write test
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
