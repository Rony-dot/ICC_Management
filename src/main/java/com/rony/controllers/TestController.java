package com.rony.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test/{id}")
    public ResponseEntity<String> testPage( @PathVariable(name = "id") String id){
        System.out.println(id);
        return ResponseEntity.ok(id);
    }
}
