package com.alansystems.bookmaking.service;

import com.alansystems.bookmaking.model.Bet;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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
        Bet existingBet = findExistingBet(bet);

        if (existingBet != null)
            sumWithExistingBet(bet, existingBet);
        else
            betList.add(bet);

        return makeOutputMessage(bet);
    }

    public String addAll(Bet[] bets) {
        StringBuilder betsResponse = new StringBuilder();
        Bet existingBet;

        for (int i = 0; i < bets.length; i++) {
            existingBet = findExistingBet(bets[i]);

            if (existingBet != null)
                sumWithExistingBet(bets[i], existingBet);
            else
                betList.add(bets[i]);


            betsResponse.append(makeOutputMessage(bets[i]));

            if (i != bets.length - 1)
                betsResponse.append("\n");
        }

        return betsResponse.toString();
    }

    private String makeOutputMessage(Bet bet) {
        double firstTeam = 0.00;
        double draw = 0.00;
        double secondTeam = 0.00;

        String betFixture = bet.getBet().getFixture();

        NumberFormat formatter = new DecimalFormat("#0.00");

        for (Bet value : betList) {
            if (value.getBet().getFixture().equals(betFixture)) {
                switch (value.getBet().getOutcome()) {
                    case "1":
                        firstTeam += value.getBet().getStake();
                        break;

                    case "X":
                        draw += value.getBet().getStake();
                        break;

                    case "2":
                        secondTeam += value.getBet().getStake();
                        break;
                }
            }
        }

        return bet.getBet().getFixture() + " " + "1: " + formatter.format(firstTeam) + " "
                + "X: " + formatter.format(draw) + " " + "2: " + formatter.format(secondTeam);
    }

    private Bet findExistingBet(Bet bet) {
        String existingRecordFixture;
        String existingRecordOutcome;

        String newRecordFixture;
        String newRecordOutcome;

        for (Bet value : betList) {
            existingRecordFixture = value.getBet().getFixture();
            existingRecordOutcome = value.getBet().getOutcome();

            newRecordFixture = bet.getBet().getFixture();
            newRecordOutcome = bet.getBet().getOutcome();

            if (existingRecordFixture.equals(newRecordFixture) && existingRecordOutcome.equals(newRecordOutcome))
                return value;
        }

        return null;
    }

    private void sumWithExistingBet(Bet newBet, Bet existingBet) {
        existingBet.getBet().setStake(existingBet.getBet().getStake() + newBet.getBet().getStake());
    }
}