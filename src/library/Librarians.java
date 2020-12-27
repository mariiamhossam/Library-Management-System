/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.Scanner;
import java.time.*;

/**
 *
 * @author yosef
 */
public class Librarians extends Users {

    public Librarians() {
    }

    public Librarians(int ID, String Email, String password, String type, String firstName, String lastName,
            String address, String cellPhone, boolean isBlocked, boolean isRent, String Book_name, LocalDate rentDate, LocalDate Deadline_Date) {
        this.ID = ID;
        this.Email = Email;
        this.password = password;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cellPhone = cellPhone;
        this.isBlocked = isBlocked;
        this.isRent = isRent;
        this.Book_name = Book_name;
        this.rentDate = rentDate;
        this.Deadline_Date = Deadline_Date;
    }

    public void Admin_page(int userIndex, Readers[] reader, Librarians[] admin, Books[] book) {
        int option1;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("                                  WELCOME TO ADMIN PAGE                               ");
            System.out.println("TO ADD A BOOK PRESS 1 ");
            System.out.println("TO ADD A USER PRESS 2 ");
            System.out.println("TO SEARCH FOR A BOOK PRESS 3 ");
            System.out.println("TO SEARCH FOR A MEMBER PRESS 4 ");
            System.out.println("TO SHOW ORDER LIST PRESS 5");
            System.out.println("TO SHOW LATE USERS PRESS 6");
            System.out.println("TO LOG OUT PRESS 7");
            option1 = input.nextInt();
            if (option1 == 1)
            {
                //call add_book function
            } 
            else if(option1==2)
             {
                
                 //call add_user function
                 int addindx;
                 String answer;
                 for(int i=0;i<100;i++){
                     addindx=i;
                    if(reader[i]==null)
                    {
                     reader[i]=new Readers();
                        add_user(reader,addindx);
                         System.out.println("DO YOU WANT TO CONTINUE: yes/no");
                    answer=input2.next();
                    if(answer.equals("no")){
                        break;
                    }
                    }
                    
                     
                     
                 }
                 
             }

            else if (option1 == 3) 
            {
                System.out.println("PLEASE ENTER BOOK NAME:");
                String bookName = input.nextLine();
                int bookIndex = searchForBook(bookName, userIndex, book);

                System.out.println("TO REMOVE THE BOOK PRESS 1");
                System.out.println("TO RENT THE BOOK PRESS 2");
                System.out.println("TO RETURN THE BOOK PRESS 3");
                option1 = input.nextInt();
                if (option1 == 1)
                {
                    //call remove function
                } else if (option1 == 2) 
                {

                    Boolean isblocked = Check_isBlocked();
                    if (isblocked)
                    {
                        System.out.println("sorry, you can't rent the book because you are blocked!");
                    }

                    Boolean isrented = Check_isRentedBefore();
                    if (isrented) 
                    {
                        System.out.println("sorry, you can't rent more than one book");
                    } else 
                    {
                        admin[userIndex].rent(bookName, book, bookIndex);
                    }
                } 
                else if (option1 == 3)
                {
                    //call return function
                }

            } 
            else if (option1 == 4)
            {
                System.out.println("PLEASE ENTER MEMBER'S ID");
                int memberID = input.nextInt();
                int memberIndex = searchForUser(memberID, reader, admin);

                if (memberIndex == -1) 
                {
                    System.out.println("NO MEMBER FOUND");
                }

                System.out.println("TO REMOVE THE USER PRESS 1");
                
                option1 = input.nextInt();

                if (option1 == 1) 
                {
                    //call remove function 
                } 
                

            }
            else if (option1 == 5)
            {
                //call show_order_list function
                System.out.println("ADD USER TO ORDER LIST PRESS 1");
                System.out.println("REMOVE USER FROM ORDER LIST PRESS 2");
                option1 = input.nextInt();
                if (option1 == 1)
                {
                    //call add to order list function  
                } else if (option1 == 2)
                {
                    //call remove function
                }
            }
            else if (option1 == 6)
            {
                Show_lateUsers(admin);
                System.out.println("TO BLOCK THEM PRESS 1");
                option1 = input.nextInt();
                if (option1 == 1) 
                {
                    Block(admin, reader);
                }

            } 
            else if (option1 == 7)
            {
                break;
            }

        }
    }

    public void add_book() {

    }

    public void remove_book() {

    }

    Scanner input2=new Scanner(System.in);
    public void add_user(Readers[]r,int indx){
        //we add readers only 
        
        for(int i=indx;i<100;indx++){
        System.out.println("PLEASE ENTER THE USER ID: ");
       
        r[indx].ID=input2.nextInt();
        
        System.out.println("PLEASE ENTER THE USER EMAIL: ");
       
        r[indx].Email=input2.next();
        System.out.println("PLEASE ENTER THE USER PASSWORD: ");
        r[indx].password=input2.next();
        r[indx].type="READER";
        System.out.println("PLEAE ENTER THE USER FIRST NAME: ");
        r[indx].firstName=input2.next();
        System.out.println("PLEASE ENTER THE USER LAST NAME: ");
        r[indx].lastName=input2.next();
        System.out.println("PLEASE ENTER THE USER ADDRESS: ");
        r[indx].address=input2.next();
        System.out.println("PLEASE ENTER THE USER CELL PHONE : ");
        r[indx].cellPhone=input2.next();
        r[indx].isBlocked=false;
        r[indx].Book_name=null;
        r[indx].rentDate=null;
        r[indx].Deadline_Date=null;
        break;
        }
    }

    public void remove_user() {

    }

    public void add_user_orderlist() {

    }

    public void remove_user_orderlist() {

    }

    @Override
    public void rent(String name, Books[] book, int bookIndex) 
    {

        if (book[bookIndex].quantity > 0) 
        {
            isRent = true;
            Book_name = name;
            book[bookIndex].quantity--;
        } 
        else 
        {
            System.out.println("the book is not available now! Do you want to be added in the waiting list?(y/n)");
            Scanner input = new Scanner(System.in);
            char choice = input.next().charAt(0);
            if (choice == 'y')
            {
                //call order list function
            }
        }
    }
@Override
    public Boolean Check_isBlocked()
    {

        if (isBlocked == true)
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }
@Override
    public boolean Check_isRentedBefore() 
    {
        if (isRent == true) 
        {
            return true;
        } else 
        {
            return false;
        }
    }
@Override
    public void Return() {

    }

    public void Show_lateUsers(Librarians[] admin)
    {
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 200; i++)
        {
            if (admin[i].Deadline_Date.compareTo(now) < 0)
            {
                System.out.println(admin[i].Email);
            }

        }
    }

    public void Block(Librarians admin[], Readers reader[])
    {
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 100; i++)
        {
            if (i < 5) 
            {
                if (admin[i].Deadline_Date.compareTo(now) < 0) 
                {
                    admin[i].isBlocked = true;
                }
            }
            if (reader[i].Deadline_Date.compareTo(now) < 0)
            {
                reader[i].isBlocked = true;
            }
        }
    }

    @Override
    public int searchForBook(String bookName, int index, Books[] book) {
        int bookIndex = -1;
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                bookIndex = i;
                System.out.println("Book Name: " + book[bookIndex].Name + "\t" + "Type: " + book[bookIndex].type + "\t" + "Quantity: " + book[bookIndex].quantity);
                System.out.println("                              -------------------------------                              ");
                break;
            }
        }

        return bookIndex;
    }

    @Override
    public int searchForUser(int memberID, Readers[] reader, Librarians[] admin) {
        int memberIndex = -1;
        for (int i = 0; i < 100; i++) {
            if (memberID == reader[i].ID) {
                memberIndex = i;
                System.out.println("Member's ID: " + reader[i].ID);
                System.out.println("Member's Name: " + reader[i].firstName + " " + reader[i].lastName);
                System.out.println("Member's Email: " + reader[i].Email);
                System.out.println("Member's Address: " + reader[i].address);
                System.out.println("Member's Phone Number: " + reader[i].cellPhone);
                System.out.println("Member's Status:");
                if (reader[i].isBlocked == true) {
                    System.out.println("\t" + "Blocked");
                } else {
                    System.out.println("\t" + "Not Blocked");
                }

                if (reader[i].isRent == true) {
                    System.out.println("\t" + "Already Rented a Book (Book Name: " + reader[i].Book_name + ", Rent Date: " + reader[i].rentDate + ", Deadline: " + reader[i].Deadline_Date + ") ");
                } else {
                    System.out.println("\t" + "Not Rented a Book");
                }

                break;
            } else if (memberID == admin[i].ID) {
                memberIndex = i;
                System.out.println("Member's ID: " + reader[i].ID + "   (Admin)");
                System.out.println("Member's Name: " + reader[i].firstName + " " + reader[i].lastName);
                System.out.println("Member's Email: " + reader[i].Email);
                System.out.println("Member's Address: " + reader[i].address);
                System.out.println("Member's Phone Number: " + reader[i].cellPhone);
                System.out.println("Member's Status:");
                if (reader[i].isBlocked == true) {
                    System.out.println("\t" + "Blocked");
                } else {
                    System.out.println("\t" + "Not Blocked");
                }

                if (reader[i].isRent == true) {
                    System.out.println("\t" + "Already Rented a Book (Book Name: " + reader[i].Book_name + ", Rent Date: " + reader[i].rentDate + ", Deadline: " + reader[i].Deadline_Date + ") ");
                } else {
                    System.out.println("\t" + "Not Rented a Book");
                }

                break;
            }
        }

        return memberIndex;
    }
}
