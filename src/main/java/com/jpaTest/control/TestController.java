package com.jpaTest.control;

import com.jpaTest.dto.TestMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(){

        TestMember m = new TestMember();

        return "test";
    }
}
