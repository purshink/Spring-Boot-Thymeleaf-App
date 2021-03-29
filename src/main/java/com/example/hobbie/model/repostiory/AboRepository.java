package com.example.hobbie.model.repostiory;

import com.example.hobbie.model.entities.Abo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AboRepository extends JpaRepository<Abo, Long> {


    List<Abo> findByClient_Id(Long id);


    Optional<Abo> findByEntriesId(Long entryId);

    List<Abo> findByHobby_BusinessOwner_Id(Long id);


}
