package com.imageupload.imageupload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomGenaratorController {

    @GetMapping
    public int genrateRandomPassword() {

        int max = 10000;
        int min = 0;
        int range = max - min + 1;

        int randPass = (int) Math.floor(Math.random() * range);
        return randPass;
    }

}
