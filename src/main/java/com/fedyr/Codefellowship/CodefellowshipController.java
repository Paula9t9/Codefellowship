package com.fedyr.Codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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

    @GetMapping("/user_profile")
    public String getProfile(Principal p, Model m){
        m.addAttribute("principal", p);
        return "user_profile";
    }


}
