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


    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String firstName, String lastName,
            String dateOfBirth, String bio) {
        CfUser newCfUser = new CfUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName,
                dateOfBirth, bio);
        cfUserRepository.save(newCfUser);
        // Log in the user after the new account is created
        Authentication authentication = new UsernamePasswordAuthenticationToken(newCfUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(Model m, Principal p){
        m.addAttribute("currentUser", null);
        return "login";
    }

    @PostMapping("/logout")
    public RedirectView logout(){
        return new RedirectView("/logout_success");
    }

    @GetMapping("/logout_success")
    public String getLogoutSuccess(Model m, Principal p){
        m.addAttribute("currentUser", null);
        return "logout_success";
    }

    @GetMapping("/signup")
    public String getSignup(Model m, Principal p){
        m.addAttribute("currentUser", null);
        return "signup";
    }

    @GetMapping("/user_profile")
    public String getProfile(Principal p, Model m){
        CfUser currentUser = cfUserRepository.findByUsername(p.getName());
                m.addAttribute("currentUser", currentUser);
                m.addAttribute("posts", currentUser.postList);
        return "user_profile";
    }

}
