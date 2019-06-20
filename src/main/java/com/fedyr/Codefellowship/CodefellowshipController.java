package com.fedyr.Codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CodefellowshipController {

    @Autowired
    CfUserRepository cfUserRepository;


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

    @GetMapping("/discover")
    public String getDiscover(Principal p, Model m){
        CfUser currentUser = cfUserRepository.findByUsername(p.getName());

        List<CfUser> possibleFollows = (List) cfUserRepository.findAll();
        possibleFollows.removeAll(currentUser.following);
        possibleFollows.remove(currentUser);

        m.addAttribute("currentUser", currentUser);
        m.addAttribute("possibleFollows", possibleFollows);

        return "discover";
    }

    @PostMapping("/follow/{id}")
    public RedirectView addFollow(@PathVariable Long id, Principal p, Model m){
        CfUser friendToFollow = cfUserRepository.findById(id).get();
        CfUser currentUser = cfUserRepository.findByUsername(p.getName());

        //Friends follow each other
        currentUser.following.add(friendToFollow);
        friendToFollow.followers.add(currentUser);

        //Save updated users to db
        cfUserRepository.save(currentUser);
        cfUserRepository.save(friendToFollow);

        return new RedirectView("/discover");
    }

    @GetMapping("/public_profile/{id}")
    public String getPublicProfile(@PathVariable Long id, Principal p, Model m){
        CfUser currentUser = cfUserRepository.findByUsername(p.getName());
        CfUser viewedUser = cfUserRepository.findById(id).get();
        m.addAttribute("currentUser", currentUser);
        m.addAttribute("posts", currentUser.postList);
        m.addAttribute("user", viewedUser);
        return "public_profile";
    }

    @GetMapping("/feed")
    public String getFeed(Principal p, Model m){
        CfUser currentUser = cfUserRepository.findByUsername(p.getName());
        List<Post> posts = new ArrayList<>();

        for (CfUser user : currentUser.following) {
            posts.addAll(user.postList);
        }

        if(posts.isEmpty()){
            posts.add(new Post("No posts to display", currentUser));
        }

        m.addAttribute("currentUser", currentUser);
        m.addAttribute("posts", posts);
        return "feed";
    }

}
