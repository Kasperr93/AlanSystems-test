package com.alansystems.bookmaking.controller;

import com.alansystems.bookmaking.model.Bet;
import com.alansystems.bookmaking.model.Match;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BetController {

    private List<Bet> betList;

    public BetController() {
        betList = new ArrayList<>();

        betList.add(new Bet("BET", new Match("Liverpool FC - FC Porto",
                "1", 20.00, 1.35)));

        betList.add(new Bet("BET", new Match("Tottenham - Manchester City",
                "X", 15.10, 2.85)));

        betList.add(new Bet("BET", new Match("Ajax - Juventus",
                "2", 42.86, 3.21)));
    }

    @GetMapping("/allBets")
    public List<Bet> getAllBets() {
        return betList;
    }
}