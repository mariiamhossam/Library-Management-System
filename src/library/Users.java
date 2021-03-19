/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.users;

import Library.Books;
import Library.users.Librarians;
import Library.users.Readers;
import java.time.*;
//import java.text.SimpleDateFormat;  
//import java.text.ParseException; 

/**
 *
 * @author yosef
 */
public abstract class Users {

    private int ID;


    public String Email;
    private String password;

    public String type;
    public String firstName;
    public String lastName;
    public String address;
    private String cellPhone;

    public boolean isBlocked;
    public boolean isRent;
    public String Book_name;
    public LocalDate rentDate;
    public LocalDate Deadline_Date;
    
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    public abstract void displayMenu();

    protected int searchForBook(String bookName, Books[] book) {
        int bookIndex = -1;
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                bookIndex = i;
                break;
            }
        }
        return bookIndex;
    }

    protected int searchForUser(int memberID, Readers[] reader, Librarians[] admin) {
        int memberIndex = -1;
        for (int i = 0; i < 100; i++) {
            if (memberID == reader[i].getID()) {
                memberIndex = i;
                break;
            }
            if (i < 5) {
                if (memberID == admin[i].getID()) {
                    memberIndex = i;
                    break;
                }

            }
        }
        return memberIndex;
    }

    protected void rent(String name, Books[] book, int bookIndex) {
        isRent = true;
        Book_name = name;
        rentDate = LocalDate.now();
        LocalDate deadline = rentDate.plusDays(7);
        Deadline_Date = deadline;
        book[bookIndex].quantity--;
        System.out.println("you have successfully rented the book! you must return it before " + Deadline_Date);
        System.out.println("_______________________________________");
    }

    protected Boolean Check_isBlocked() {
        if (isBlocked == true) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean Check_isRentedBefore() {
        if (isRent == true) {
            return true;
        } else {
            return false;
        }
    }
    
    protected void update_orderlist(Books[] book, Readers[] reader, Librarians[] admin) {
        for (int i = 0; i < 200; i++) {
            int j = 1;
            if(book[i].quantity>0){
            while (book[i].counter > 0) {
                int id = book[i].orderList[j - 1];
                book[i].orderList[j - 1] = book[i].orderList[j];
                book[i].counter--;
                j++;
                
                boolean User_Flag = false;
                for(int ii = 0; ii < 100; ii++)
                {
                    if(reader[ii].ID == id){
                        reader[ii].isRent = true;
                        User_Flag = true;
                        break;
                    }
                }
                for(int ii = 0; ii < 100 && !User_Flag; ii++)
                {
                    if(admin[ii].ID == id){
                        admin[ii].isRent = true;
                        break;
                    }
                }
            }
        }
        }
    }


    protected void Return_book(Books[] b, int book_indx) {
        b[book_indx].quantity++;
        isRent = false;
        Book_name="empty";
        rentDate = LocalDate.of(2030, 1, 2);
        Deadline_Date = LocalDate.of(2030, 1, 2);
        System.out.println("The book has been successfully returned");
        System.out.println("_______________________________________");
    }
    
    public void add_user_to_orderlist(int User_ID, Books[] book, int bookIndex) {
        book[bookIndex].orderList[book[bookIndex].counter] = User_ID;
        book[bookIndex].counter++;
        System.out.println("The addition process is successfully done");
        System.out.println("_______________________________________");
    }
}
    
