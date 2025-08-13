package com.one.consulta.consultalivrosone.controller;

import com.one.consulta.consultalivrosone.DTO.AuthorResponseDTO;
import com.one.consulta.consultalivrosone.model.Author;
import com.one.consulta.consultalivrosone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{year}")
    public ResponseEntity<List<AuthorResponseDTO>> findStillAlive(@PathVariable Integer year) {
        return  ResponseEntity.ok(authorService.stillAlive(year));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> findAll() {
        return  ResponseEntity.ok(authorService.findAll());
    }
}
