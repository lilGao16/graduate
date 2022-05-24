package com.example.healthdata.service;

import com.example.healthdata.entity.Data;
import com.example.healthdata.entity.Record;

import java.util.List;

public interface DataService {
    List<Data> searchAll();
    Data searchByName(String name);
    List<Data> searchMale();
    List<Data> searchFemale();
    void update1(Data data);
    void update2(Record record);
    void insert(Data data);
    void delete(String name);
    List<Data> fuzzySearch(String name);
    Record getRecord(String name);
    List<Record> getAllRecord();
    void insert2(Record record);
    void delete2(String name);
}
