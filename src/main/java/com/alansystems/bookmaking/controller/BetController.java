package com.alansystems.bookmaking.controller;

import com.alansystems.bookmaking.model.Bet;
import com.alansystems.bookmaking.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BetController {

    private BetService service;

    @Autowired
    public BetController(BetService service) {
        this.service = service;
    }

    @GetMapping("/allBets")
    public List<Bet> getAllBets() {
        return service.getAll();
    }

    @PostMapping("add")
    public void addOneBet(@RequestBody Bet bet) {
        service.addOne(bet);
    }

    @PostMapping("addAll")
    public void addAllBets(@RequestBody Bet[] bets) {
        service.addAll(bets);
    }
}