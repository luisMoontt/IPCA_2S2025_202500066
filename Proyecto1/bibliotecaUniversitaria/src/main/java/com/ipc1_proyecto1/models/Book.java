package com.ipc1_proyecto1.models;

/**
 * @author Luis Monterroso
 */
public class Book {
    private String id_b;
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private int yearDeparture;
    private int stock;

    public Book(String id_b, String ISBN, String title, String author, String genre, int yearDeparture, int stock) {
        this.id_b = id_b;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearDeparture = yearDeparture;
        this.stock = stock;
    }

    public String getId_b() {
        return id_b;
    }

    public void setId_b(String id_b) {
        this.id_b = id_b;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearDeparture() {
        return yearDeparture;
    }

    public void setYearDeparture(int yearDeparture) {
        this.yearDeparture = yearDeparture;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
