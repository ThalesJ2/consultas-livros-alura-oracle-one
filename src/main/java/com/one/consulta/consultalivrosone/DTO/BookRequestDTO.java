package com.one.consulta.consultalivrosone.DTO;

import com.one.consulta.consultalivrosone.model.Author;

import java.util.List;

public record BookRequestDTO(
        Integer id,
        String title,
        Boolean copyright,
        String media_type,
        Integer download_count,
        List<Author> authors
) {


}
