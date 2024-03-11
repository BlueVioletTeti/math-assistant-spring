package com.geeksforless.mathassistantspring.repository;

import com.geeksforless.mathassistantspring.model.Root;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RootRepository extends JpaRepository<Root, Long> {
    @Override
    @Query("SELECT r FROM Root r LEFT JOIN FETCH r.equations e")
    List<Root> findAll();
}
