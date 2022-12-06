package com.example.spingbootblogapplication.config;

import com.example.spingbootblogapplication.models.Account;
import com.example.spingbootblogapplication.models.Authority;
import com.example.spingbootblogapplication.models.Post;
import com.example.spingbootblogapplication.repositories.AuthorityRepository;
import com.example.spingbootblogapplication.services.AccountService;
import com.example.spingbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeeData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("abv");
            account1.setPassword("123");

            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("gmail");
            account2.setPassword("123");

            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("title of p 1");
            post1.setBody("body of p1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("title of p 2");
            post2.setBody("de da znam");
            post2.setAccount(account2);

            Post post3 = new Post();
            post3.setTitle("title of p3");
            post3.setBody("i tuka taka");
            post3.setAccount(account1);

            postService.save(post1);
            postService.save(post2);
            postService.save(post3);
        }
    }
}