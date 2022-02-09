package com.qzero.blog.data.repository;

import com.qzero.blog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByUsernameAndPasswordHash(String username,String passwordHash);

    User getByUsernameAndPasswordHash(String username,String passwordHash);

}
