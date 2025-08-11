package com.one.consulta.consultalivrosone.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.one.consulta.consultalivrosone.model.Book;
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

    public List<Book> findBook(String title){
        address = address +"?search="+title;
        address = removeSpace(address);
        JsonObject object = gson.fromJson(apiService.callAPI(address), JsonObject.class);

        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(object.get("results"), listType);

    }

    public List<Book> findAll(){
        JsonObject object = gson.fromJson(apiService.callAPI(address), JsonObject.class);
        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();

        return gson.fromJson(object.get("results"), listType);
    }



    public String removeSpace(String  content){
        return content.replace(" ","%20");
    }
}
