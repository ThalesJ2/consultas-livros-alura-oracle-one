package com.one.consulta.consultalivrosone.repository;

import com.one.consulta.consultalivrosone.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Boolean existsByName(String name);
    Author findByName(String name);
}
