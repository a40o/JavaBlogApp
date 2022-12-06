package com.example.spingbootblogapplication.repositories;

import com.example.spingbootblogapplication.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
