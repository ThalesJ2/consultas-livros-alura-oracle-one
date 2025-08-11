package com.one.consulta.consultalivrosone.controller;

import com.one.consulta.consultalivrosone.DTO.BookRequestDTO;
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
    public ResponseEntity<List<Book>> findBook(@ModelAttribute BookRequestDTO dto) {

        if(dto.id() == null && dto.title() == null && dto.author() == null && dto.language() == null)
            return  ResponseEntity.ok(service.findAll());

        return ResponseEntity.ok(service.findBook(dto.title()));
    }
}
