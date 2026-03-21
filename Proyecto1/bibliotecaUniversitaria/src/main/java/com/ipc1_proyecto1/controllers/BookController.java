package com.ipc1_proyecto1.controllers;
import com.ipc1_proyecto1.models.Book;

/**
 * @author Luis Monterroso
 */
public class BookController {
    private Book[] books;
    private int booksLimit = 100;
    private int totalBooks;
    
    public BookController(){
        this.books = new Book[booksLimit];
        this.totalBooks = 0;
    }
    // CREATE AND LOAD /////////////////////////////
    public void loadBook(String id_b, String ISBN, String title, String author, String genre, int yearDeparture, int stock){
        books[totalBooks] = new Book(id_b, ISBN, title, author, genre, yearDeparture, stock);
        totalBooks++;
    }
    public void createBook(String ISBN, String title, String author, String genre, int yearDeparture, int stock){
        int bookCode = 1000 + totalBooks;
        String id_b = String.valueOf(bookCode);
        
        books[totalBooks] = new Book(id_b, ISBN, title, author, genre, yearDeparture, stock);
        totalBooks++;
    }
    // READ ////////////////////////////////
    public Book[] listBooks(){
        Book[] result = new Book[totalBooks];
        for (int i=0; i<totalBooks; i++){
            result[i] = books[i];
        }
        return result;
    }
    // ///////////////////////////////////////
    public Book findBook(String ISBN){
        Book bo = null;
        if(bookExists(ISBN)){
            for (int i=0; i<totalBooks; i++){
                if (books[i].getISBN().equals(ISBN)){
                    bo = books[i];
                }
            }
        }
        return bo;
    }
    public boolean bookExists(String ISBN){
        for (int i=0; i<totalBooks; i++){
            if(books[i].getISBN().equals(ISBN)){
                return true;
            }
        }
        return false;
    }
    
}
