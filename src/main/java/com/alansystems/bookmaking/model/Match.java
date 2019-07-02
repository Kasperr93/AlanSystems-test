package com.alansystems.bookmaking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Match {
    private String fixture;
    private String outcome;
    private double stake;
    private double odds;
}