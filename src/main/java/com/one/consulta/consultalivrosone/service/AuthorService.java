package com.one.consulta.consultalivrosone.service;

import com.one.consulta.consultalivrosone.DTO.AuthorResponseDTO;
import com.one.consulta.consultalivrosone.DTO.BookResponseDTO;
import com.one.consulta.consultalivrosone.model.Author;
import com.one.consulta.consultalivrosone.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    public AuthorResponseDTO findByName(String name){
        Author author = authorRepository.findByName(name);

        if(author == null){
            throw new RuntimeException("Author not found");
        }

        return new AuthorResponseDTO(author);
    }

    public List<AuthorResponseDTO> findAll(){
        return  authorRepository.findAll().stream().map(AuthorResponseDTO::new).toList();
    }

    public List<AuthorResponseDTO> stillAlive(Integer year){
        return authorRepository.liveInaYear(year).stream().map(AuthorResponseDTO::new).toList();
    }
}
