package com.example.healthdata.mapper;

import com.example.healthdata.entity.Data;
import com.example.healthdata.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {
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
