package org.uuzics.simplearchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String helloWorld() {
        return "Hello world!";
    }
}
