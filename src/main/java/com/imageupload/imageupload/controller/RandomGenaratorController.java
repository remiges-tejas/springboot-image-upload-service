package com.imageupload.imageupload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomGenaratorController {

    @GetMapping
    public double genrateRandomPassword() {

        double randPass = Math.random() * 10;
        return randPass;
    }

}
