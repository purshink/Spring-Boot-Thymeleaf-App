package com.example.hobbie.model.repostiory;

import com.example.hobbie.model.entities.Abo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboRepository extends JpaRepository<Abo, Long> {
}
