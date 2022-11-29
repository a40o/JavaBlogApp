package com.example.spingbootblogapplication.config;

import com.example.spingbootblogapplication.models.Post;
import com.example.spingbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeeData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {

            Post post1 = new Post();
            post1.setTitle("title of p 1");
            post1.setBody("body of p1");

            Post post2 = new Post();
            post2.setTitle("title of p 2");
            post2.setBody("de da znam");

            Post post3 = new Post();
            post3.setTitle("title of p3");
            post3.setBody("i tuka taka");

            postService.save(post1);
            postService.save(post2);
            postService.save(post3);
        }
    }
}
