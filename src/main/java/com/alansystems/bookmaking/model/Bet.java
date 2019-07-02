package com.alansystems.bookmaking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bet {
    private String type;
    private Match bet;
}