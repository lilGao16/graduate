package com.example.healthdata.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private String name;
    private String frequency;
    private String keeptime1;
    private String keeptime2;
    private String ability;
    private String smoke;
    private String drug;
}
