package com.leggo.fit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test()
    {
        return ("test done");
    }

    @RequestMapping(value="/test1",method = RequestMethod.GET)
    public ResponseEntity<?> test1()
    {
        return ResponseEntity.ok("Test 1 is done");
    }
}
