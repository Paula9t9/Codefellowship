package com.fedyr.Codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {


    @Autowired
    PostRepository postRepository;

    @Autowired
    CfUserRepository userRepository;

    @PostMapping("/posts")
    public RedirectView addPost(String body, Principal p){
        Post newPost = new Post();
        CfUser creator = userRepository.findByUsername(p.getName());
        newPost.body = body;
        newPost.createdBy = creator;
        postRepository.save(newPost);
        return new RedirectView("/user_profile");
    }
}
