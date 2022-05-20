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
    void update(Data data);
    void insert(Data data);
    void delete(String name);
    List<Data> fuzzySearch(String name);
    Record getRecord(String name);
}
