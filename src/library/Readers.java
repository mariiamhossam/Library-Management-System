/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.time.LocalDate;
import java.util.Scanner;
/**
 *
 * @author yosef
 */
public class Readers extends Users {
    public Readers()
    {
        
    }
     public Readers(int ID,String Email,String password,String type,String firstName,String lastName,
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
     public void Reader_page(int userIndex,Readers[] reader,Librarians[] admin,Books[]book)
     {
         int option1;
          Scanner input =new Scanner(System.in);
          while(true){
            System.out.println("                                  WELCOME TO READER PAGE                               ");
            System.out.println("TO SEARCH FOR A BOOK PRESS 1");
            System.out.println("TO SEARCH FOR A MEMBER PRESS 2");
            System.out.println("TO LOG OUT PRESS 3");
             option1=input.nextInt();
             if(option1==1)
             {
                System.out.println("PLEASE ENTER BOOK NAME:");
                String bookName=input.next();
                int bookIndex=searchForBook(bookName, userIndex, book);
                if(bookIndex==-1)
                {
                    System.out.println("NO BOOK FOUND");
                }
                    
                else{                
                System.out.println("TO RENT THE BOOK PRESS 1");
                System.out.println("TO RETURN THE BOOK PRESS 2");
                option1=input.nextInt();
                if(option1==1)
                {
                   Boolean isblocked=reader[userIndex].Check_isBlocked();
                   Boolean isrented=reader[userIndex].Check_isRentedBefore();
                    if(isblocked==true)
                    {
                        System.out.println("sorry, you can't rent the book because you are blocked!");
                    }
                  
                   else if(isrented==true)
                    {
                        System.out.println("sorry, you can't rent more than one book");
                    }
                    else if(isblocked==false && isrented==false && book[bookIndex].quantity>0)
                    {
                        reader[userIndex].rent(bookName,book,bookIndex);
                        
                     }
                 
                     else
                     {
                          System.out.println("the book is not available now! Do you want to be added in the order list?(y/n)");
                            char choice=input.next().charAt(0);
                             if(choice=='y')
                               {
                              //call add to order list function
                               }
                    
                     }
                    
                }
                   
                else if(option1==2)
                {
                    //call return_book function
                }
             }
            }
          
            else if(option1==2)
            {
                System.out.println("PLEASE ENTER MEMBER'S ID:");
                int memberID=input.nextInt();
                int memberIndex=searchForUser(memberID,reader,admin);
                
                if(memberIndex==-1)
                {
                    System.out.println("NO MEMBER FOUND");
                }
                
                    
            }
            
             else if(option1==3)
            {
                break;
            }    
        }
     }
     public void add_self()
     {
         
     }
     
    
     @Override
       public void rent(String name,Books[]book,int bookIndex)
    {
        
        
        
        isRent=true;
        Book_name=name;
        rentDate=LocalDate.now();
        LocalDate deadline=rentDate.plusDays(7);
        Deadline_Date=deadline;
        book[bookIndex].quantity--;
        System.out.println("you have successfully rented the book! you must return it before"  +Deadline_Date);
        
        
     
        
       
       }
       @Override
    public Boolean Check_isBlocked(){
    
     if(isBlocked==true)
       {
           return true;
       }
     else
         return false;
    }
    @Override
        public boolean Check_isRentedBefore()
        {
            if(isRent==true)
            {
                return true;
            }
            else
            return false;
        }
        
    @Override
    public void Return_book()
    {
        
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
                System.out.println("Book Name: "+book[bookIndex].Name+"\t"+book[bookIndex].type);
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
                break;
            }
            if(i<5){
            if(memberID==admin[i].ID)
            {
                memberIndex=i;
                System.out.println("Member's ID: "+admin[i].ID+"   (Admin)");
                System.out.println("Member's Name: "+admin[i].firstName+" "+admin[i].lastName);
                System.out.println("Member's Email: "+admin[i].Email);
                break;
            }
            }
        }
        
        return memberIndex;
    }
}
