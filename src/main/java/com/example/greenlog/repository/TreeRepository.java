package com.example.greenlog.repository;

import com.example.greenlog.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository
        extends JpaRepository<Tree, Long> {
}