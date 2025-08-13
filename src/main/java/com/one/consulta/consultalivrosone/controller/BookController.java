package com.one.consulta.consultalivrosone.controller;

import com.one.consulta.consultalivrosone.DTO.BookCountByLanguageResponseDTO;
import com.one.consulta.consultalivrosone.DTO.BookResponseDTO;
import com.one.consulta.consultalivrosone.model.Book;
import com.one.consulta.consultalivrosone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findBook(@RequestParam(required = false) String title) {

        if(title == null){
            return  ResponseEntity.ok(service.findAll());
        }
        return ResponseEntity.ok(service.findBook(title));
    }

    @GetMapping("/{language}")
    public ResponseEntity<BookCountByLanguageResponseDTO> countByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(service.countByLanguage(language));
    }
}
