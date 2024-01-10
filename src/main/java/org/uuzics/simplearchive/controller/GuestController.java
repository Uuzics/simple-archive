package org.uuzics.simplearchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleHomePage() {
        return "homepage";
    }
}
