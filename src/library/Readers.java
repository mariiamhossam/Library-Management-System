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
     public void Reader_page(int index,Readers reader[],Books[]book)
     {
         int option1;
          Scanner input =new Scanner(System.in);
          while(true){
            System.out.println("                                  WELCOME TO READER PAGE                               ");
            System.out.println("SEARCH FOR A BOOK PRESS 1");
            System.out.println("SEARCH FOR A USER PRESS 2");
            System.out.println("LOG OUT PRESS 3");
             option1=input.nextInt();
             if(option1==1)
             {
                      System.out.println("PLEASE ENTER BOOK NAME");
                     String name=input.next();
                     //call search for the book index function
                      System.out.println("VIEW BOOK INFORMATION PRESS 1");                     
                      System.out.println("RENT THE BOOK PRESS 2");
                      System.out.println("RETURN THE BOOK PRESS 2");
                      option1=input.nextInt();
                      if(option1==1)
                      {
                        //call view book info function  
                      }
                      else if(option1==2)
                      {
                             Boolean isblocked=Check_isBlocked();
                          if(isblocked)
                          {
                              System.out.println("sorry, you can't rent the book because you are blocked!");
                          }
                          Boolean isrented=Check_isRentedBefore();
                          if(isrented)
                          {
                              System.out.println("sorry, you can't rent more than one book");
                          }
                          else{
                          reader[index].rent(name,book,index);
                          }
                      }
                      else if(option1==3)
                      {
                         //call return function
                      }
                  }
                  else if(option1==2)
                  {
                      System.out.println("PLEASE ENTER USER NAME");
                        //variable
                        //call view user's data function 
                    
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
       public void rent(String name,Books[]book,int index)
    {
        
        
        if(book[0].quantity>0)
        {
        isRent=true;
        Book_name=name;
        rentDate=LocalDate.now();
        LocalDate deadline=rentDate.plusDays(7);
        Deadline_Date=deadline;
        book[0].quantity--;
        }
        else
        {
            System.out.println("the book is not available now! Do you want to be added in the order list?(y/n)");
             Scanner input =new Scanner(System.in);
             char choice=input.next().charAt(0);
             if(choice=='y')
             {
                 //call order list function
             }
        }
        
       
       
       
       }
    public Boolean Check_isBlocked(){
    
     if(isBlocked==true)
       {
           return true;
       }
     else
         return false;
    }
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
     public void searchForBook()
    {
        
    }
     
     @Override
      public void searchForUser()
    {
        
    }
}