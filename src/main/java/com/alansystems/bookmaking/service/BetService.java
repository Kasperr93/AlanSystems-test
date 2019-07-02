package com.alansystems.bookmaking.service;

import com.alansystems.bookmaking.model.Bet;
import org.springframework.stereotype.Service;

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

    public void addOne(Bet bet) {
        betList.add(bet);
    }

    public void addAll(Bet[] bets) {
        betList.addAll(Arrays.asList(bets));
    }
}