package com.tject.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
public class LoginController {
    @Autowired
    private AdminService userService;

    @RequestMapping("login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        return userService.login(username, password);
    }
    @RequestMapping("register")
    public JsonResult register(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("realname") String realname,
                               @RequestParam("phonenumber") String phonenumber) {
        return userService.register(username, password,realname,phonenumber);
    }
}
