package com.fedyr.Codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class CodefellowshipController {


    @GetMapping("/")
    public String getHome(Principal p, Model m){
        m.addAttribute("principal", p);
        //If the user isn't logged in, jut call them visitor.
        if(p == null){
            m.addAttribute("user", "visitor");
        }else {
            m.addAttribute("user", p.getName());
        }
        return "codefellowship";
    }


    @GetMapping("/codefellowship")
    public String getCodefellowship(Principal p, Model m){
        //If the user isn't logged in, jut call them visitor.
        if(p == null){
            m.addAttribute("user", "visitor");
        }else {
            m.addAttribute("user", p.getName());
        }

        return "codefellowship";
    }




}
