package com.sparta.test.repositpry;

import com.sparta.test.entity.SalesPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesPostRepository extends JpaRepository<SalesPost, Long> {
}
