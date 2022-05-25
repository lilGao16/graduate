package com.example.healthdata.controller;

import com.example.healthdata.entity.Login;
import com.example.healthdata.entity.User;
import com.example.healthdata.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public String login(@RequestBody Login logininfo) {
        User user = userMapper.selectOne(logininfo.getUsername());
        if(user == null || !logininfo.getPassword().equals(user.getPassword())) {
            return "No";
        }
        else {
            return user.getRole();
        }
    }

    @RequestMapping("/getusers")
    public List<User> getUsers() {
        return userMapper.select();
    }

    @RequestMapping("/adduser")
    public String addUser(@RequestBody User user) {
        userMapper.insert(user);
        return "Add OK!";
    }

    @RequestMapping("/updateuser")
    public String updateUser(@RequestBody User user) {
        userMapper.update(user);
        return "Update OK!";
    }

    @RequestMapping("/deleteuser/{username}")
    public String deleteUser(@PathVariable("username")String username) {
        userMapper.delete(username);
        return "Delete OK!";
    }
}
