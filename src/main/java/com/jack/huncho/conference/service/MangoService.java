package com.jack.huncho.conference.service;

import com.jack.huncho.conference.model.Mango;
import org.springframework.stereotype.Service;

@Service
public class MangoService {

    public Mango serveMango(Mango mango) {
        // logic goes here
        mango.washMango();
        mango.peelMango();
        mango.cutMango();
        return mango;
    }
}
