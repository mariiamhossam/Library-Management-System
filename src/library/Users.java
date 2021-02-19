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

    public abstract void displayMenu();

    public int searchForBook(String bookName, Books[] book) {
        int bookIndex = -1;
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                bookIndex = i;
                break;
            }
        }
        return bookIndex;
    }

    public int searchForUser(int memberID, Readers[] reader, Librarians[] admin) {
        int memberIndex = -1;
        for (int i = 0; i < 100; i++) {
            if (memberID == reader[i].ID) {
                memberIndex = i;
                break;
            }
            if (i < 5) {
                if (memberID == admin[i].ID) {
                    memberIndex = i;
                    break;
                }

            }
        }
        return memberIndex;
    }

    public void rent(String name, Books[] book, int bookIndex) {
        isRent = true;
        Book_name = name;
        rentDate = LocalDate.now();
        LocalDate deadline = rentDate.plusDays(7);
        Deadline_Date = deadline;
        book[bookIndex].quantity--;
        System.out.println("you have successfully rented the book! you must return it before " + Deadline_Date);
        System.out.println("_______________________________________");
    }

    public Boolean Check_isBlocked() {
        if (isBlocked == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Check_isRentedBefore() {
        if (isRent == true) {
            return true;
        } else {
            return false;
        }
    }

    public void update_orderlist(Books[] book) {
        for (int i = 0; i < 200; i++) {
            int j = 1;
            while (book[i].counter > 0) {
                book[i].orderList[j - 1] = book[i].orderList[j];
                book[i].counter--;
                j++;
            }
        }
    }

    public void Return_book(Books b[], int book_indx, Readers[] r, int user_indx) {
        b[book_indx].quantity++;
        r[user_indx].isRent = false;
        r[user_indx].Book_name="empty";
        r[user_indx].rentDate = LocalDate.of(2030, 1, 2);
        r[user_indx].Deadline_Date = LocalDate.of(2030, 1, 2);
        System.out.println("The book has been successfully returned");
        System.out.println("_______________________________________");
    }
}
