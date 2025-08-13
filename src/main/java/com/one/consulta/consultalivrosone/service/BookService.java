package com.one.consulta.consultalivrosone.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.one.consulta.consultalivrosone.DTO.BookRequestDTO;
import com.one.consulta.consultalivrosone.DTO.BookResponseDTO;
import com.one.consulta.consultalivrosone.model.Author;
import com.one.consulta.consultalivrosone.model.Book;
import com.one.consulta.consultalivrosone.repository.AuthorRepository;
import com.one.consulta.consultalivrosone.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private APIService apiService;
    private String address = "https://gutendex.com/books/";

    @Autowired
    private Gson gson;

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookResponseDTO> findBook(String title){
        List<Book> books = new ArrayList<>();
        List<BookResponseDTO> responseDTO = new ArrayList<>();
        if(!repository.existsByTitle(title)){
            String auxAddress = address;
            auxAddress = auxAddress +"?search="+title;
            auxAddress = removeSpace(auxAddress);
            String result = apiService.callAPI(auxAddress);
            JsonObject object = gson.fromJson(result, JsonObject.class);

            Type listType = new TypeToken<ArrayList<BookRequestDTO>>(){}.getType();
             List<BookRequestDTO> dto = gson.fromJson(object.get("results"), listType);
            JsonObject languageField = JsonParser.parseString(result).getAsJsonObject();
            String language =  languageField.get("results").getAsJsonArray().get(0).getAsJsonObject()
                    .get("languages").getAsJsonArray().asList().getFirst().getAsString();
            books.add(new Book(dto.getFirst()));
            books.getFirst().setLanguage(language);
            Author author = books.getFirst().getAuthor();
            if(!authorRepository.existsByName(author.getName())){
              author  =   authorRepository.save(author);
              books.getFirst().setAuthor(author);
            }else{
                author = authorRepository.findByName(author.getName());
                books.getFirst().setAuthor(author);
            }

            responseDTO.add(new BookResponseDTO(repository.save(books.getFirst())));
            return  responseDTO;

        }

        responseDTO.add(new BookResponseDTO(repository.findByTitle(title)));
        return responseDTO;

    }

    public List<BookResponseDTO> findAll(){
        String result = apiService.callAPI(address);
        JsonObject object = gson.fromJson(result, JsonObject.class);
        Type listType = new TypeToken<ArrayList<BookRequestDTO>>(){}.getType();
        JsonObject languageField = JsonParser.parseString(result).getAsJsonObject();
        String language =  languageField.get("results").getAsJsonArray().get(0).getAsJsonObject()
                .get("languages").getAsJsonArray().asList().getFirst().getAsString();
        List<BookRequestDTO> books = gson.fromJson(object.get("results"), listType);
        for (BookRequestDTO book : books) {
            Author author = book.authors().getFirst();

            if(!authorRepository.existsByName(author.getName())){
                author = authorRepository.save(author);
                repository.save(new Book(book,author,language));
            }
        }
        return repository.findAll().stream().map(BookResponseDTO::new).toList();
    }



    public String removeSpace(String  content){
        return content.replace(" ","%20");
    }
}
