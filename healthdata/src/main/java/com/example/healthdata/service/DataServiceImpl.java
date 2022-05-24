package com.example.healthdata.service;

import com.example.healthdata.entity.Data;
import com.example.healthdata.entity.Record;
import com.example.healthdata.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService{

    @Autowired
    DataMapper dataMapper;

    public List<Data> searchAll() {
        return dataMapper.searchAll();
    };
    public Data searchByName(String name) {
        return dataMapper.searchByName(name);
    }
    public List<Data> searchMale() {
        return dataMapper.searchMale();
    };
    public List<Data> searchFemale() {
        return dataMapper.searchFemale();
    };
    public void update1(Data data) {dataMapper.update1(data);}
    public void update2(Record record) {dataMapper.update2(record);}
    public void insert(Data data) {
        dataMapper.insert(data);
    }
    public void delete(String name) {
        dataMapper.delete(name);
    }
    public List<Data> fuzzySearch(String name) {
        return dataMapper.fuzzySearch(name);
    };
    public Record getRecord(String name) {
        return dataMapper.getRecord(name);
    }
    public void insert2(Record record) {
        dataMapper.insert2(record);
    }
    public void delete2(String name) {
        dataMapper.delete2(name);
    }
    public List<Record> getAllRecord() {return dataMapper.getAllRecord();}
}
