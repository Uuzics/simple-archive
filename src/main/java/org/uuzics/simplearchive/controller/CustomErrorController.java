package org.uuzics.simplearchive.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (null != status) {
            int statusCode = Integer.parseInt(status.toString());

            if (HttpStatus.NOT_FOUND.value() == statusCode) {
                return "not_found";
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.value() == statusCode) {
                model.addAttribute("title", "Internal Error");
                model.addAttribute("heading", "Technical issue occurred");
                model.addAttribute("description", """
                        Some part of the system was not working properly.
                        Please contact site administrator if this keeps happening.
                        """);
                return "custom_error";
            }
        }

        model.addAttribute("title", "Unexpected Error");
        model.addAttribute("heading", "Something went wrong");
        model.addAttribute("description", """
                Something unexpected happened.
                Please contact site administrator.
                """);
        return "custom_error";
    }
}
