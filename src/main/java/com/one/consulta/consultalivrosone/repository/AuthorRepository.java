package com.one.consulta.consultalivrosone.repository;

import com.one.consulta.consultalivrosone.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Boolean existsByName(String name);
    Author findByName(String name);


    @Query(nativeQuery = true ,value = "select * from author where author.birth_year < :year and  author.death_year > :year")
    List<Author> liveInaYear(Integer year);
}
