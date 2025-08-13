package com.one.consulta.consultalivrosone.DTO;

import com.one.consulta.consultalivrosone.model.Author;

public record AuthorResponseDTO(
         Integer id,
         String name,
         Integer birth_year,
         Integer death_year
) {

    public AuthorResponseDTO(Author author) {
        this(author.getId(), author.getName(), author.getBirth_year(), author.getDeath_year());
    }
}
