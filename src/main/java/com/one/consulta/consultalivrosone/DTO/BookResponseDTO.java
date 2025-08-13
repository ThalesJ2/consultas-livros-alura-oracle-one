package com.one.consulta.consultalivrosone.DTO;

import com.one.consulta.consultalivrosone.model.Book;

public record BookResponseDTO(
        Integer id,
        String title,
        String language,
        Boolean copyright,
        String media_type,
        Integer download_count,
        AuthorResponseDTO author
) {
    public BookResponseDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getLanguage(), book.getCopyright(), book.getMedia_type(), book.getDownload_count(), new AuthorResponseDTO(book.getAuthor()));
    }

}
