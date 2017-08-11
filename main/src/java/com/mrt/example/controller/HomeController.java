package com.mrt.example.controller;

import com.mrt.example.dao.User;
import com.mrt.example.dao.UserRepository;
import com.mrt.example.model.ResultModel;
import com.mrt.example.model.ValuesModel;
import com.mrt.example.simplex.SimplexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by katermar on 4/23/2017.
 */
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return StreamSupport.<User>stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());//new ArrayList<User>(userRepository.findAll());
    }

    @RequestMapping(value = "/error.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String error() {
        return "error.html";
    }

    @ResponseBody
    @GetMapping(value = "/get")
    public boolean newUser(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password,
                           @RequestParam(value="role", defaultValue="USER") String role) {

        this.userRepository.save(new User(username, password, role));

        return true;
    }

    @RequestMapping(value = "/result", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel result(@RequestBody ValuesModel values) {
        SimplexService service = new SimplexService();
        ResultModel resultModel = new ResultModel();

        if (service.test(values)) {
            resultModel.setArray(service.getResultTable());
            resultModel.setMessage("Оптимальное решение найдено");
        } else {
            resultModel.setMessage("Нет оптимального решения");
        }
        return resultModel;
    }

}
