package com.example.healthdata.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class ImData {
    private String name;
    private String gender;
    private String birthday;
    private String address;
    private String phoneNum;
    private Double height;
    private Double weight;
    private Double heat;
    private Double pulse;
    private String pressure;
    public void inject(Data data) {
        this.address = data.getAddress();
        this.name = data.getName();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.birthday = df.format(data.getBirthday());
        this.phoneNum = data.getPhoneNum();
        this.gender = data.getGender();
        this.height = data.getHeight();
        this.weight = data.getWeight();
        this.heat = data.getHeat();
        this.pulse = data.getPulse();
        this.pressure = data.getPressure();
    }
}
