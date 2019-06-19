package com.fedyr.Codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
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




}
