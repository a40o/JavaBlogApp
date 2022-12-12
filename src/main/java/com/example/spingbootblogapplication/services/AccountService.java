package com.example.spingbootblogapplication.services;

import com.example.spingbootblogapplication.models.Account;
import com.example.spingbootblogapplication.models.Authority;
import com.example.spingbootblogapplication.repositories.AccountRepository;
import com.example.spingbootblogapplication.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Account save(Account account) {

        if (account.getId() == null) {
            if (account.getAuthorities().isEmpty()) {
                Set<Authority> authorities = new HashSet<>();
                authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
                account.setAuthorities(authorities);
            }
        }
        account.setRole(getAccountRole(account));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findOneByEmailIgnoreCase(email);
    }

    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    public String getAccountRole(Account account){
        if(account.getAuthorities().equals("ROLE_ADMIN")) {
            return "admin";
        } else {
            return "user";
        }
    }
}