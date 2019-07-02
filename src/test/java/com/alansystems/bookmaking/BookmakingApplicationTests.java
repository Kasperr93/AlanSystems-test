package com.alansystems.bookmaking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookmakingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllBets() throws Exception {
        mockMvc.perform(get("/allBets")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].type", is("BET")))
                .andExpect(jsonPath("$[0].bet.fixture", is("Liverpool FC - FC Porto")))
                .andExpect(jsonPath("$[0].bet.outcome", is("1")))
                .andExpect(jsonPath("$[0].bet.stake", is(20.00)))
                .andExpect(jsonPath("$[0].bet.odds", is(1.35)))

                .andExpect(jsonPath("$[1].type", is("BET")))
                .andExpect(jsonPath("$[1].bet.fixture", is("Tottenham - Manchester City")))
                .andExpect(jsonPath("$[1].bet.outcome", is("X")))
                .andExpect(jsonPath("$[1].bet.stake", is(15.10)))
                .andExpect(jsonPath("$[1].bet.odds", is(2.85)))

                .andExpect(jsonPath("$[2].type", is("BET")))
                .andExpect(jsonPath("$[2].bet.fixture", is("Ajax - Juventus")))
                .andExpect(jsonPath("$[2].bet.outcome", is("2")))
                .andExpect(jsonPath("$[2].bet.stake", is(42.86)))
                .andExpect(jsonPath("$[2].bet.odds", is(3.21)));
    }
}