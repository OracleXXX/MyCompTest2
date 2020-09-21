package com.funti.hello.spring.thymeleaf.controller;

import com.funti.hello.spring.thymeleaf.entity.User;
import com.funti.hello.spring.thymeleaf.exception.UserNotExistException;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

//    @RequestMapping({"/", "index"})
//    public String index() {
//
//        return "index";
//    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")) {
            throw new UserNotExistException();
        }
        return "Hello World";
    }


    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}
//http://ec2-3-21-127-147.us-east-2.compute.amazonaws.com:8080/main.html