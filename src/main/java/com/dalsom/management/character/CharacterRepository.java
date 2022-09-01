package com.dalsom.management.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Characters, Long> {

    Optional<Characters> findByCharacterDataCharacterName(String name);

}
