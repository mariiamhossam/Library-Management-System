/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.time.*;
//import java.text.SimpleDateFormat;  
//import java.text.ParseException; 

/**
 *
 * @author yosef
 */
public abstract class Users {

    public int ID;
    public String Email;
    public String password;
    public String type;
    public String firstName;
    public String lastName;
    public String address;
    public String cellPhone;
    public boolean isBlocked;
    public boolean isRent;
    public String Book_name;
    public LocalDate rentDate;
    public LocalDate Deadline_Date;

    public int searchForBook(String bookName, int index, Books[] book)
    {
        int bookIndex = -1;
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                bookIndex = i;
                break;
            }
        }
        return bookIndex;
    }

    public abstract int searchForUser(int memberID, Readers[] reader, Librarians[] admin);

    public void rent(String name, Books[] book, int bookIndex)
    {
        isRent = true;
        Book_name = name;
        rentDate = LocalDate.now();
        LocalDate deadline = rentDate.plusDays(7);
        Deadline_Date = deadline;
        book[bookIndex].quantity--;
        System.out.println("you have successfully rented the book! you must return it before " + Deadline_Date);
        System.out.println("_______________________________________");
    }

    public abstract Boolean Check_isBlocked();

    public abstract boolean Check_isRentedBefore();

    public void Return_book(Books b[], int book_indx, Readers[] r, int user_indx)
    {
        b[book_indx].quantity++;
        r[user_indx].isRent = false;
        r[user_indx].rentDate = LocalDate.of(2030, 1, 2);
        r[user_indx].Deadline_Date = LocalDate.of(2030, 1, 2);
        System.out.println("The book has been successfully returned");
        System.out.println("_______________________________________");
    }
}
