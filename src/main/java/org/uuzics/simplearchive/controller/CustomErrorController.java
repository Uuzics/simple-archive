/*
 * Copyright 2024 Uuzics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
