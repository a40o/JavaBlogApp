package com.example.spingbootblogapplication.controllers;

import com.example.spingbootblogapplication.models.Account;
import com.example.spingbootblogapplication.models.Post;
import com.example.spingbootblogapplication.services.AccountService;
import com.example.spingbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Post> posts = postService.getAll();
        if (principal != null) {
            model.addAttribute("logged", principal);
            Optional<Account> optionalAccount = accountService.findOneByEmail(principal.getName());
            Account account = new Account();
            account.setId(optionalAccount.get().getId());
            model.addAttribute("accountId", account.getId());
        }
        model.addAttribute("posts", posts);
        return "home";
    }

}