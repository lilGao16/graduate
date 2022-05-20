package com.example.healthdata.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private String name;
    private String gender;
    private Date birthday;
    private String address;
    private String phoneNum;
    private Double height;
    private Double weight;
    private Double heat;
    private Double pulse;
    private String pressure;
}
