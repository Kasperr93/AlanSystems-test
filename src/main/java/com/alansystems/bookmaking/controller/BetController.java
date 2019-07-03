package com.alansystems.bookmaking.controller;

import com.alansystems.bookmaking.exceptions.IncorrectValueException;
import com.alansystems.bookmaking.model.Bet;
import com.alansystems.bookmaking.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class BetController {

    private BetService service;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public BetController(BetService service) {
        this.service = service;
    }

    @GetMapping("/allBets")
    public List<Bet> getAllBets() {
        return service.getAll();
    }

    @PostMapping("add")
    public ResponseEntity<String> addOneBet(@RequestBody Bet bet) {
        if (bet.getBet().getOutcome().equals("1") || bet.getBet().getOutcome().equals("X")
                || bet.getBet().getOutcome().equals("2")) {

            String response = service.addOne(bet);
            logger.log(Level.INFO, "Response: {0}", "\n" + response);

            return new ResponseEntity<>("Your bet is: \n" + response, HttpStatus.CREATED);
        }

        logger.log(Level.WARNING, "Response: {0}", "The bet has not been added");
        return new ResponseEntity<>(new IncorrectValueException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addAll")
    public ResponseEntity<String> addAllBets(@RequestBody Bet[] bets) {
        for (Bet bet : bets) {
            if (!bet.getBet().getOutcome().equals("1") && !bet.getBet().getOutcome().equals("X")
                    && !bet.getBet().getOutcome().equals("2")) {
                logger.log(Level.WARNING, "Response: {0}", "The bets has not been added");
                return new ResponseEntity<>(new IncorrectValueException().getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        String response = service.addAll(bets);
        logger.log(Level.INFO, "Response: {0}", "\n" + response);

        return new ResponseEntity<>("Your bets are: \n" + response, HttpStatus.CREATED);
    }
}