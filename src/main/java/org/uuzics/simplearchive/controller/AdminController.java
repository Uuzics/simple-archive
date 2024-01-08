package org.uuzics.simplearchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/dash", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleDashboard(Model model){
        return "admin_dash";
    }
    @RequestMapping(value = "/new", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleEditor(Model model){
        return "admin_editor";
    }
}
