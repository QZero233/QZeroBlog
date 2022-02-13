package com.qzero.blog.data.repository;

import com.qzero.blog.data.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
}
