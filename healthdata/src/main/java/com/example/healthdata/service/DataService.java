package com.example.healthdata.service;

import com.example.healthdata.entity.Data;
import com.example.healthdata.entity.Record;

import java.util.List;

public interface DataService {
    List<Data> searchAll();
    Data searchByName(String name);
    List<Data> searchMale();
    List<Data> searchFemale();
    void update(Data data);
    void insert(Data data);
    void delete(String name);
    List<Data> fuzzySearch(String name);
    Record getRecord(String name);
}
