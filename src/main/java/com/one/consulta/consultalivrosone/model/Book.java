package com.one.consulta.consultalivrosone.model;

import java.util.List;

public class Book {

    private Integer id;
    private String title;
    private List<Author> authors;
    private List<String> summaries;
    private List<Translator> translators;
    private List<String> subjects;
    private List<String> bookshelves;
    private Boolean copyright;
    private String media_type;
    private Integer download_count;


    public Book() {
    }

    public Book(Integer id, String title, List<Author> authors, List<String> summaries, List<Translator> translators, List<String> subjects, List<String> bookshelves, Boolean copyright, String media_type, Integer download_count) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.summaries = summaries;
        this.translators = translators;
        this.subjects = subjects;
        this.bookshelves = bookshelves;
        this.copyright = copyright;
        this.media_type = media_type;
        this.download_count = download_count;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<String> summaries) {
        this.summaries = summaries;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
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

    public List<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Translator> translators) {
        this.translators = translators;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", summaries=" + summaries +
                ", translators=" + translators +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", copyright=" + copyright +
                ", media_type='" + media_type + '\'' +
                ", download_count=" + download_count +
                '}';
    }
}
