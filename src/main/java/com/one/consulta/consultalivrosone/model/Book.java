package com.one.consulta.consultalivrosone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.one.consulta.consultalivrosone.DTO.BookRequestDTO;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Book {

    @Id
    private Integer id;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id",nullable = false)
    private Author author;
    private String language;
    private Boolean copyright;
    private String media_type;
    private Integer download_count;


    public Book() {
    }

    public Book(Integer id, String title, Author author, Boolean copyright, String media_type, Integer download_count) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copyright = copyright;
        this.media_type = media_type;
        this.download_count = download_count;
    }

    public Book(BookRequestDTO dto){
        this.id = dto.id();
        this.title = dto.title();
        this.copyright = dto.copyright();
        this.media_type = dto.media_type();
        this.download_count = dto.download_count();
        this.author = dto.authors().getFirst();
    }
    public Book(BookRequestDTO dto, Author author, String language){
        this.id = dto.id();
        this.language = language;
        this.title = dto.title();
        this.copyright = dto.copyright();
        this.media_type = dto.media_type();
        this.download_count = dto.download_count();
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", copyright=" + copyright +
                ", media_type='" + media_type + '\'' +
                ", download_count=" + download_count +
                '}';
    }
}
