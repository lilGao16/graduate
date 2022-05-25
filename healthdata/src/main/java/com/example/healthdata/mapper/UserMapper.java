package com.example.healthdata.mapper;

import com.example.healthdata.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);
    void update(User user);
    void delete(String username);
    List<User> select();
    User selectOne(String username);
}
