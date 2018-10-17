package com.grit.learning.controller;

import com.grit.learning.AppResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/helloWorld")
public class HelloWorldController {

    @Autowired
    private AppResultManager res;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String testException(String userName, String callback) {
        Assert.notNull(userName, "userName can't found");
        return res.success(userName, callback);
    }
}
