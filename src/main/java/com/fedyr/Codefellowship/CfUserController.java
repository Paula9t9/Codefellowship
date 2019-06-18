package com.fedyr.Codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class CfUserController {

    @Autowired
    CfUserRepository cfUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getHome(Principal p, Model m){
        m.addAttribute("principal", p);
        return "codefellowship";
    }

    @PostMapping("/users")
    public RedirectView createUser(String username, String password){
        CfUser newCfUser = new CfUser(username, bCryptPasswordEncoder.encode(password));
        cfUserRepository.save(newCfUser);
        // Log in the user after the new account is created
        Authentication authentication = new UsernamePasswordAuthenticationToken(newCfUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
