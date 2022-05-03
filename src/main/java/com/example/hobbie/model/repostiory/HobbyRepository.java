package com.example.hobbie.model.repostiory;

import com.example.hobbie.model.entities.Hobby;
import com.example.hobbie.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    List<Hobby> findAllByBusinessOwnerBusinessName(String businessName);

    List<Hobby> findAllByLocation(Location location);
}
