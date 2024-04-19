package com.mycompany.managementsystem;

import java.util.ArrayList;
import java.util.List;

class Member {
    private String name;
    private String email;
    private List<Book> outBooks;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.outBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getOutBooks() {
        return outBooks;
    }

    public void borrowBook(Book book) {
        outBooks.add(book);
    }

    public void returnBook(Book book) {
        outBooks.remove(book);
    }
}
