package com.mycompany.managementsystem;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


class Library {
    public List<Book> books;
    public List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void deleteMember(Member member) {
        members.remove(member);
    }

    public List<Book> searchBooks(String query) {
        List<Book> result = new ArrayList<>();
        query = query.toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) || 
                book.getAuthor().toLowerCase().contains(query) ||
                book.getIsbn().toLowerCase().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void checkoutBook(Member member, Book book) throws Exception {
        if (!book.isAvailable()) {
            throw new Exception("Book not available for checkout.");
        }
        book.toggleAvailability();
        member.borrowBook(book);
    }

    public void returnBook(Member member, Book book) throws Exception {
        if (!member.getOutBooks().contains(book)) {
            throw new Exception("Book not found in member's borrowed books.");
        }
        book.toggleAvailability();
        member.returnBook(book);
    }

    public boolean validateEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void addBooksFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of books you want to add: ");
        int numBooks = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numBooks; i++) {
            System.out.println("Enter details for Book " + (i + 1) + ": ");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            Book book = new Book(title, author, isbn);
            addBook(book);
            System.out.println("Book added successfully.");
        }
    }
}
