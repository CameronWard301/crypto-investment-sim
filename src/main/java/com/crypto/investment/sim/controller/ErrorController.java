package com.crypto.investment.sim.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null){
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()){
                // 404 error
                model.addAttribute("errorTitle", "Crypto Sim | 404 Error");
                model.addAttribute("errorHeading", "Error: 404<br>Sorry, we couldn't find the page you were looking for :(");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                //500 error
                model.addAttribute("errorTitle", "Crypto Sim | 500 Error");
                model.addAttribute("errorHeading", "Error: 500<br>Sorry, we have experienced an internal server error :(");
            } else{
                model.addAttribute("errorTitle", "Crypto Sim | Unknown Error");
                model.addAttribute("errorHeading", "Oops! Something went wrong.");
            }
        } else {
            model.addAttribute("errorTitle", "Crypto Sim | Unknown Error");
            model.addAttribute("errorHeading", "Oops! Something went wrong.");
        }
    return "error";
    }
}
