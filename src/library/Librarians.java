/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Scanner;
import java.time.*;
/**
 *
 * @author yosef
 */
public class Librarians extends Users {

    public Librarians() {
    }
    
      public Librarians(int ID,String Email,String password,String type,String firstName,String lastName,
              String address,String cellPhone,boolean isBlocked,boolean isRent,String Book_name,LocalDate rentDate,LocalDate Deadline_Date)
    {
       this.ID=ID;
       this.Email=Email;
       this.password=password;
       this.type=type;
       this.firstName=firstName;
       this.lastName=lastName;
       this.address=address;
       this.cellPhone=cellPhone;
       this.isBlocked=isBlocked;
       this.isRent=isRent;
       this.Book_name=Book_name;
       this.rentDate=rentDate;
        this.Deadline_Date=Deadline_Date;
    }
      public void Admin_page(int userIndex,Readers[] reader,Librarians[] admin,Books[]book)
      {
          int option1;
          Scanner input =new Scanner(System.in);
          while(true){
            System.out.println("                                  WELCOME TO ADMIN PAGE                               ");
            System.out.println("TO ADD A BOOK PRESS 1 ");
            System.out.println("TO ADD A USER PRESS 2 ");
            System.out.println("TO SEARCH FOR A BOOK PRESS 3 ");
            System.out.println("TO SEARCH FOR A MEMBER PRESS 4 ");
            System.out.println("TO SHOW ORDER LIST PRESS 5");
            System.out.println("TO SHOW LATE USERS PRESS 6");
            System.out.println("TO LOG OUT PRESS 7");
             option1=input.nextInt();
             if(option1==1)
             {
                 //call add_book function
             }
             else if(option1==2)
             {
                 //call add_user function
             }
             else if(option1==3)
             {
                      System.out.println("PLEASE ENTER BOOK NAME");
                      String bookName=input.nextLine();
                      int bookIndex=searchForBook(Book_name, userIndex, book);
                      
                      System.out.println("TO REMOVE THE BOOK PRESS 1");                     
                      System.out.println("TO RENT THE BOOK PRESS 2");
                      option1=input.nextInt();
                      if(option1==1)
                      {
                        //call remove function
                      }
                      else if(option1==2)
                      {
                            //admin[0].rent();
                         admin[userIndex].rent(bookName, book,bookIndex);
                      }
                     
                  }
                  else if(option1==4)
                  {
                    System.out.println("PLEASE ENTER MEMBER'S ID");
                    int memberID=input.nextInt();
                    int memberIndex=searchForUser(memberID,reader,admin);
                
                    if(memberIndex==-1)
                    {
                       System.out.println("NO MEMBER FOUND");
                    }
                   
                    System.out.println("TO REMOVE THE USER PRESS 1");                     
                    System.out.println("TO BLOCK THE USER PRESS 2");
                    option1=input.nextInt();
                
                    if(option1==1)
                    {
                       //call remove function 
                    }
                    else if(option1==2)
                    {
                       //call block function
                    }
                   
                 }
             
            
            else if(option1==5)
            {
                //call show_order_list function
                System.out.println("ADD USER TO ORDER LIST PRESS 1");
                System.out.println("REMOVE USER FROM ORDER LIST PRESS 2");
                option1=input.nextInt();
                      if(option1==1)
                      {
                        //call add to order list function  
                      }
                      else if(option1==2)
                      {
                          //call remove function
                      }
            }
            else if(option1==6)
            {
                Show_late_users(admin);
                System.out.println("TO BLOCK THEM PRESS 1");
                option1=input.nextInt();
                if(option1==1){
                Block(admin);
                }
                
            }
            else if(option1==7)
            {
                break;
            }

            
        }
      }
    public void add_book(){
        
    }
    public void remove_book(){
        
    }
    public void add_user(){
        
    }
    public void remove_user(){
        
    }
    public void add_user_orderlist(){
        
    }
    public void remove_user_orderlist(){
        
    }
    public void block(){
        
    }
       @Override
    public void rent(String name,Books[]book,int index)
    {
     
        if(book[0].quantity>0)
        {
        isRent=true;
        Book_name=name;
        book[0].quantity--;
        }
        else
        {
            System.out.println("the book is not available now! Do you want to be added in the waiting list?(y/n)");
             Scanner input =new Scanner(System.in);
             char choice=input.next().charAt(0);
             if(choice=='y')
             {
                 //call order list function
             }
    }
    }
    public void Show_late_users(Librarians[]admin)
    {
         LocalDate now = LocalDate.now(); 
        for(int i=0;i<200;i++)
        {
           if(admin[i].Deadline_Date.compareTo(now)<0)
           {
               System.out.println(admin[i].Email);
           }
                    
        }
    }
    public void Block(Librarians admin[])
    {
        LocalDate now = LocalDate.now();
        for(int i=0;i<200;i++)
        {
            if(admin[i].Deadline_Date.compareTo(now)<0)
           {
               admin[i].isBlocked=true;
           }
        }
    }
    @Override
      public int searchForBook(String bookName,int index,Books[]book)
    {
        int bookIndex=-1;
        for(int i=0;i<200;i++)
        {
            if(bookName.equals(book[i].Name))
            {
                bookIndex=i;
                System.out.println("Book Name: "+book[bookIndex].Name+"\t"+"Quantity: "+book[bookIndex].quantity+"\t"+"Type: "+book[bookIndex].type);
                System.out.println("                              -------------------------------                              ");
                break;
            }
        }
       
        return bookIndex;
    }
     
     @Override
      public int searchForUser(int memberID,Readers[] reader,Librarians[] admin)
    {
       int memberIndex=-1;
        for(int i=0;i<100;i++)
        {
            if(memberID==reader[i].ID)
            {
                memberIndex=i;
                System.out.println("Member's ID: "+reader[i].ID);
                System.out.println("Member's Name: "+reader[i].firstName+" "+reader[i].lastName);
                System.out.println("Member's Email: "+reader[i].Email);
                System.out.println("Member's Address: "+reader[i].address);
                System.out.println("Member's Phone Number: "+reader[i].cellPhone);
                System.out.println("Member's Status:");
                if(reader[i].isBlocked==true)
                {
                    System.out.println("\t"+"Blocked");
                }
                else
                {
                    System.out.println("\t"+"Not Blocked");
                }
                
                if(reader[i].isRent==true)
                {
                    System.out.println("\t"+"Already Rented a Book (Book Name: "+reader[i].Book_name+", Rent Date: "+reader[i].rentDate+", Deadline: "+reader[i].Deadline_Date+") ");
                }
                else
                {
                    System.out.println("\t"+"Not Rented a Book");
                }
                
                break;
            }
            
            else if(memberID==admin[i].ID)
            {
                memberIndex=i;
                System.out.println("Member's ID: "+reader[i].ID+"   (Admin)");
                System.out.println("Member's Name: "+reader[i].firstName+" "+reader[i].lastName);
                System.out.println("Member's Email: "+reader[i].Email);
                System.out.println("Member's Address: "+reader[i].address);
                System.out.println("Member's Phone Number: "+reader[i].cellPhone);
                System.out.println("Member's Status:");
                if(reader[i].isBlocked==true)
                {
                    System.out.println("\t"+"Blocked");
                }
                else
                {
                    System.out.println("\t"+"Not Blocked");
                }
                
                if(reader[i].isRent==true)
                {
                    System.out.println("\t"+"Already Rented a Book (Book Name: "+reader[i].Book_name+", Rent Date: "+reader[i].rentDate+", Deadline: "+reader[i].Deadline_Date+") ");
                }
                else
                {
                    System.out.println("\t"+"Not Rented a Book");
                }
                
                break;
            }
        }
        
        return memberIndex;
    }
}
