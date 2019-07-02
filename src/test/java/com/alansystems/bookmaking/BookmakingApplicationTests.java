package com.alansystems.bookmaking;

import com.alansystems.bookmaking.model.Bet;
import com.alansystems.bookmaking.model.Match;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookmakingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

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

    @Test
    public void shouldAddOneBet() throws Exception {
        Bet bet = new Bet("BET", new Match("FC Albin - FC Barcelona", "X", 5.00, 2.12));

        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(bet)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/allBets")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[3].type", is("BET")))
                .andExpect(jsonPath("$[3].bet.fixture", is("FC Albin - FC Barcelona")))
                .andExpect(jsonPath("$[3].bet.outcome", is("X")))
                .andExpect(jsonPath("$[3].bet.stake", is(5.00)))
                .andExpect(jsonPath("$[3].bet.odds", is(2.12)));
    }

    @Test
    public void shouldAddAllBets() throws Exception {
        Bet[] bets = new Bet[5];

        bets[0] = new Bet("BET", new Match("FC A - FC B", "1", 7.00, 22.22));
        bets[1] = new Bet("BET", new Match("FC C - FC D", "X", 46.80, 3.12));
        bets[2] = new Bet("BET", new Match("FC E - FC F", "2", 2.22, 12.26));
        bets[3] = new Bet("BET", new Match("FC G - FC H", "2", 13.20, 6.66));
        bets[4] = new Bet("BET", new Match("FC I - FC J", "1", 25.42, 6.88));

        mockMvc.perform(post("/addAll").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(bets)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/allBets")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[3].type", is("BET")))
                .andExpect(jsonPath("$[3].bet.fixture", is("FC A - FC B")))
                .andExpect(jsonPath("$[3].bet.outcome", is("1")))
                .andExpect(jsonPath("$[3].bet.stake", is(7.00)))
                .andExpect(jsonPath("$[3].bet.odds", is(22.22)))

                .andExpect(jsonPath("$[4].type", is("BET")))
                .andExpect(jsonPath("$[4].bet.fixture", is("FC C - FC D")))
                .andExpect(jsonPath("$[4].bet.outcome", is("X")))
                .andExpect(jsonPath("$[4].bet.stake", is(46.80)))
                .andExpect(jsonPath("$[4].bet.odds", is(3.12)))

                .andExpect(jsonPath("$[5].type", is("BET")))
                .andExpect(jsonPath("$[5].bet.fixture", is("FC E - FC F")))
                .andExpect(jsonPath("$[5].bet.outcome", is("2")))
                .andExpect(jsonPath("$[5].bet.stake", is(2.22)))
                .andExpect(jsonPath("$[5].bet.odds", is(12.26)))

                .andExpect(jsonPath("$[6].type", is("BET")))
                .andExpect(jsonPath("$[6].bet.fixture", is("FC G - FC H")))
                .andExpect(jsonPath("$[6].bet.outcome", is("2")))
                .andExpect(jsonPath("$[6].bet.stake", is(13.20)))
                .andExpect(jsonPath("$[6].bet.odds", is(6.66)))

                .andExpect(jsonPath("$[7].type", is("BET")))
                .andExpect(jsonPath("$[7].bet.fixture", is("FC I - FC J")))
                .andExpect(jsonPath("$[7].bet.outcome", is("1")))
                .andExpect(jsonPath("$[7].bet.stake", is(25.42)))
                .andExpect(jsonPath("$[7].bet.odds", is(6.88)));
    }
}