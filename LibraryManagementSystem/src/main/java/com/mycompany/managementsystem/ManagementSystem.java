package com.mycompany.managementsystem;
import java.util.List;
import java.util.Scanner;


public class ManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        //Menu options for user ease
        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Search Books");
            System.out.println("2. Register as Member");
            System.out.println("3. Delete Member");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Add Books");
            System.out.println("7. List Available Books");
            System.out.println("8. Close");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            //switch cases to validate user inputs and provide the suitible output
            switch (choice) {
                case 1:
                    System.out.print("Enter book name/ISBN: ");
                    String query = scanner.nextLine();
                    List<Book> searchResults = library.searchBooks(query);
                    if (searchResults.isEmpty()) {
                        System.out.println("No books found matching your requested ISBN/name.");
                    } else {
                        System.out.println("Search Results:");
                        for (Book book : searchResults) {
                            System.out.println(book.getTitle() + " by " + book.getAuthor());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    if (library.validateEmail(email)) {
                        Member member = new Member(name, email);
                        library.addMember(member);
                        System.out.println("Member registered successfully.");
                    } else {
                        System.out.println("Invalid email format.");
                    }
                    break;
                case 3:
                    System.out.print("Enter email of member to delete: ");
                    String emailToDelete = scanner.nextLine();
                    Member memberToDelete = null;
                    for (Member member : library.members) {
                        if (member.getEmail().equals(emailToDelete)) {
                            memberToDelete = member;
                            break;
                        }
                    }
                    if (memberToDelete != null) {
                        library.deleteMember(memberToDelete);
                        System.out.println("Member deleted successfully.");
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                case 4:
                     System.out.print("Enter email of member: ");
                    String memberEmail = scanner.nextLine();
                    Member borrower = null;
                    for (Member member : library.members) {
                        if (member.getEmail().equals(memberEmail)) {
                            borrower = member;
                            break;
                        }
                    }
                    if (borrower == null) {
                        System.out.println("Member not found.");
                        break;
                    }

                    System.out.print("Enter ISBN of book to borrow: ");
                    String isbnToBorrow = scanner.nextLine();
                    Book bookToBorrow = null;
                    for (Book book : library.books) {
                        if (book.getIsbn().equals(isbnToBorrow)) {
                            bookToBorrow = book;
                            break;
                        }
                    }
                    if (bookToBorrow == null) {
                        System.out.println("Book not found.");
                        break;
                    }

                    try {
                        library.checkoutBook(borrower, bookToBorrow);
                        System.out.println("Book borrowed successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Enter email of member: ");
                    String returningMemberEmail = scanner.nextLine();
                    Member returningMember = null;
                    for (Member member : library.members) {
                        if (member.getEmail().equals(returningMemberEmail)) {
                            returningMember = member;
                        }
                    }
                    break;
                    
                case 6:
                    library.addBooksFromUserInput();
                    break;
                case 7:
                    List<Book> availableBooks = library.getAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("No available books.");
                    } else {
                        System.out.println("Available Books:");
                        for (Book book : availableBooks) {
                            System.out.println(book.getTitle() + " by " + book.getAuthor());
                        }
                    }
                    break;
                case 8:
                    System.out.println("Closing...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}