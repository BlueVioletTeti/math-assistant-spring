package com.geeksforless.mathassistantspring.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeksforless.mathassistantspring.model.Equation;
import com.geeksforless.mathassistantspring.model.Root;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RootControllerTest {
    protected static MockMvc mockMvc;

    private static final Long DEFAULT_ID_1 = 1L;

    private static final String DEFAULT_EQUATION_1 = "2*x+5=17";

    private static final double DEFAULT_ROOT_1 = 6d;

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
    void save_ValidRoot_Success() throws Exception {
        //TODO: fix test
//        Equation equation = new Equation();
//        Root expected = getRoot().setEquations(Set.of(equation));
//
//        String jsonRequest = objectMapper.writeValueAsString(expected);
//        MvcResult result = mockMvc.perform(
//                        post("/api/roots")
//                                .content(jsonRequest)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Root actual = objectMapper.readValue(result.getResponse().getContentAsString(),
//                Root.class);
//        assertNotNull(actual);
//        assertNotNull(actual.getId());
//        assertEquals(expected.getRoot(), actual.getRoot());
//        EqualsBuilder.reflectionEquals(expected, actual, "id");
    }

    private static Root getRoot() {
        return new Root()
                .setRoot(DEFAULT_ROOT_1)
                .setId(DEFAULT_ID_1);
    }

    private static Equation getEquation() {
        return new Equation()
                .setEquation(DEFAULT_EQUATION_1)
                .setId(DEFAULT_ID_1)
                .setRoots(Set.of(getRoot()));
    }
}
