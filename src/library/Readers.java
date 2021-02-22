/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.users;

import Library.Books;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author yosef
 */
public class Readers extends Users {

    public Readers() {

    }

    public Readers(int ID, String Email, String password, String type, String firstName, String lastName,
            String address, String cellPhone, boolean isBlocked, boolean isRent, String Book_name, LocalDate rentDate, LocalDate Deadline_Date) {
        setID(ID); 
        this.Email = Email;
        setPassword(password);
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        setCellPhone(cellPhone); 
        this.isBlocked = isBlocked;
        this.isRent = isRent;
        this.Book_name = Book_name;
        this.rentDate = rentDate;
        this.Deadline_Date = Deadline_Date;
    }

    public void Reader_page(int userIndex, Readers[] reader, Librarians[] admin, Books[] book) {
        System.out.println("\t\t\t\t* WELCOME TO READER PAGE *\t\t\t\t");
        int option1;
        Scanner input = new Scanner(System.in);
        while (true) {

            displayMenu();
            update_orderlist(book);
            option1 = input.nextInt();
            if (option1 == 1) {
                System.out.println("PLEASE ENTER BOOK NAME:");
                String bookName = input.next();
                int bookIndex = searchForBook(bookName, book);
                System.out.println("Book Name: " + book[bookIndex].Name + "\t" + "Type: " + book[bookIndex].type + "\t\t" + "Quantity: " + book[bookIndex].quantity);
                System.out.println("_______________________________________");

                if (bookIndex == -1) {
                    System.out.println("NO BOOK FOUND!");
                    System.out.println("_______________________________________");
                    continue;
                } else {
                    System.out.println("Do you want to rent or return this book? (y/n)");
                    char choice = input.next().charAt(0);
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println("TO RENT THE BOOK PRESS 1");
                        if(reader[userIndex].Book_name.equals(book[bookIndex].Name))
                        {
                        System.out.println("TO RETURN THE BOOK PRESS 2");
                        }
                        option1 = input.nextInt();
                        if (option1 == 1) {
                            Boolean isblocked = reader[userIndex].Check_isBlocked();
                            Boolean isrented = reader[userIndex].Check_isRentedBefore();
                            if (isblocked == true) {
                                System.out.println("Sorry, you can't rent the book because you are blocked!");
                            } else if (isrented == true) {
                                System.out.println("Sorry, you can't rent more than one book");
                            } else if (isblocked == false && isrented == false && book[bookIndex].quantity > 0) {
                                reader[userIndex].rent(bookName, book, bookIndex);
                            } else {
                                System.out.println("The book is not available now! Do you want to be added in the order list?(y/n)");
                                choice = input.next().charAt(0);
                                if (choice == 'y' || choice == 'Y') {
                                    //call add to order list function
                                    bookIndex = searchForBook(bookName, book);
                                    int userID = reader[userIndex].getID();
                                    add_self_toorderlist(userID, book, bookIndex);
                                }

                            }
                        } else if (option1 == 2 && reader[userIndex].Book_name.equals(book[bookIndex].Name)) {
                            //call return_book function
                            reader[userIndex].Return_book(book, bookIndex);

                        }
                    } else if (choice == 'n' || choice == 'N') {
                        System.out.println("______________________________");
                        continue;
                    }
                }
            } else if (option1 == 2) {
                System.out.println("PLEASE ENTER MEMBER'S ID:");
                int memberID = input.nextInt();
                int memberIndex = searchForUser(memberID, reader, admin);
                System.out.println("_______________________________________");

                if (memberIndex == -1) {
                    System.out.println("NO MEMBER FOUND!");
                    System.out.println("_______________________________________");
                    continue;
                }
                else{
                    if(memberID==reader[memberIndex].getID())
            {
               
                System.out.println("Member's ID: "+reader[memberIndex].getID());
                System.out.println("Member's Name: "+reader[memberIndex].firstName+" "+reader[memberIndex].lastName);
                System.out.println("Member's Email: "+reader[memberIndex].Email);
                break;
            }
            if(memberIndex<5)
            {
              if(memberID==admin[memberIndex].getID())
              {
                
                System.out.println("Member's ID: "+admin[memberIndex].getID()+"   (Admin)");
                System.out.println("Member's Name: "+admin[memberIndex].firstName+" "+admin[memberIndex].lastName);
                System.out.println("Member's Email: "+admin[memberIndex].Email);
                System.out.println("_______________________________________");
                }

            } else if (option1 == 3) {
                break;
            }
        }
    }
        }
    }

    public void displayMenu() {
        System.out.println("TO SEARCH FOR A BOOK PRESS 1");
        System.out.println("TO SEARCH FOR A MEMBER PRESS 2");
        System.out.println("TO LOG OUT PRESS 3");
    }

    public void add_self_toorderlist(int User_ID, Books[] book, int bookIndex) {
        book[bookIndex].orderList[book[bookIndex].counter] = User_ID;
        book[bookIndex].counter++;
        System.out.println("You have been successfully added to the order list");
        System.out.println("_______________________________________");
    }

}

