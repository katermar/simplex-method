package com.mrt.example.controller;

import com.mrt.example.dao.User;
import com.mrt.example.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by USER on 4/23/2017.
 */
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public List<User> getAllUsers() {

        return StreamSupport.<User>stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());//new ArrayList<User>(userRepository.findAll());
    }

    @ResponseBody
    @GetMapping(value = "/get")
    public boolean newUser(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password,
                           @RequestParam(value="role", defaultValue="USER") String role) {

        this.userRepository.save(new User(username, password, role));

        return true;
    }

}
