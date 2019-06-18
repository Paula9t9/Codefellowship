package com.fedyr.Codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CodefellowshipController {

    @GetMapping("/codefellowship")
    public String getCodefellowship(){
        return "codefellowship";
    }

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }
}
