package com.rescreen.rescreen_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RescreenController {

    @GetMapping("/rescreen")
    public String apiTest(){
        return "Api Test Successfull";
    }


}
