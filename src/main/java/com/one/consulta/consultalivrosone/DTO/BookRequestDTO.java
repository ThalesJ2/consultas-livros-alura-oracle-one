package com.one.consulta.consultalivrosone.DTO;

public record BookRequestDTO(
        String title,
        Integer id,
        String  author,
        String language
) {
}
