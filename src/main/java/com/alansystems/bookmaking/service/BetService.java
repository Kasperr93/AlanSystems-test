package com.alansystems.bookmaking.service;

import com.alansystems.bookmaking.model.Bet;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BetService {
    private List<Bet> betList;

    public BetService() {
        betList = new ArrayList<>();
    }

    public List<Bet> getAll() {
        return betList;
    }

    public String addOne(Bet bet) {
        betList.add(bet);

        return makeOutputMessage(bet);
    }

    public String addAll(Bet[] bets) {
        StringBuilder betsResponse = new StringBuilder();
        betList.addAll(Arrays.asList(bets));

        for (int i = 0; i < bets.length; i++) {
            betsResponse.append(makeOutputMessage(bets[i]));

            if (i != bets.length - 1)
                betsResponse.append("\n");
        }

        return betsResponse.toString();
    }

    private String makeOutputMessage(Bet bet) {
        String response;
        double firstTeam = 0.00;
        double draw = 0.00;
        double secondTeam = 0.00;

        NumberFormat formatter = new DecimalFormat("#0.00");

        switch (bet.getBet().getOutcome()) {
            case "1":
                firstTeam += bet.getBet().getStake();
                break;

            case "X":
                draw += bet.getBet().getStake();
                break;

            case "2":
                secondTeam += bet.getBet().getStake();
                break;
        }

        response = bet.getBet().getFixture() + " " + "1: " + formatter.format(firstTeam) + " "
                + "X: " + formatter.format(draw) + " " + "2: " + formatter.format(secondTeam);

        return response;
    }
}