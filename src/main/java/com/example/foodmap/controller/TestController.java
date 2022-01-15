package com.example.foodmap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/log")
    public String log(){
        log.error("에러입니다.");
        return "hello";
    }
}
