package com.jpaTest.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // localhost:8080
    @GetMapping("/")
    public String home(){
        return "index";
    }
}
