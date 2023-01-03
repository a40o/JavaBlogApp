package com.example.spingbootblogapplication.controllers;

import com.example.spingbootblogapplication.models.Account;
import com.example.spingbootblogapplication.services.AccountService;
import com.example.spingbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
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

    @PostMapping("/accounts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updateAccount(@PathVariable Long id, Account account, BindingResult result, Model model, Principal principal) {

        Optional<Account> optionalAccount = accountService.getById(id);
        if (optionalAccount.isPresent() || principal.getName().equals("gmail")) {
            Account existingAccount = optionalAccount.get();

            existingAccount.setFirstName(account.getFirstName());
            existingAccount.setLastName(account.getLastName());
            existingAccount.setEmail(account.getEmail());
            existingAccount.setPassword(account.getPassword());

            accountService.save(existingAccount);
        }

        return "redirect:/accounts/" + account.getId();
    }

    @GetMapping("/accounts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getAccountForEdit(@PathVariable Long id, Model model, Principal principal) {

        // find post by id
        Optional<Account> optionalAccount = accountService.getById(id);
        // if post exist put it in model
        if (optionalAccount.isPresent() || principal.getName().equals("gmail")) {
            Account account = optionalAccount.get();
            if (account.getEmail().equals(principal.getName())) {
                model.addAttribute("account", account);
                return "account_edit";
            } else {
                return "404";
            }
        }
        return "404";
    }
}

