package com.example.spingbootblogapplication.controllers;

import com.example.spingbootblogapplication.models.Account;
import com.example.spingbootblogapplication.services.AccountService;
import com.example.spingbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts/{id}")
    public String getAccount(@PathVariable Long id, Model model) {

        Optional<Account> optionalAccount = this.accountService.getById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            model.addAttribute("account", account);
            return "account";
        } else {
            return "404";
        }
    }
}

