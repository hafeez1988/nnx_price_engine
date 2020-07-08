package com.nnx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

/**
 * The home controller class for health check.
 * 
 * @author hafeez
 */
@ApiIgnore
@Controller
public class HomeController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/")
    public @ResponseBody String loadHomePage() {
        return "99X Price Engine is up and running.";
    }
}
