package com.jack.huncho.conference.controller;

import com.jack.huncho.conference.model.Mango;
import com.jack.huncho.conference.service.MangoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MangoController {

    @Autowired
    MangoService service;

    Mango juicyMango = new Mango();


    @GetMapping("/get/me/mangos")
    Mango receiveJuicyMango() {
        return service.serveMango(juicyMango);
    }
}



