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
   
    
    public void Admin_page(int userIndex, Readers[] reader, Librarians[] admin, Books[] book)
    {
        System.out.println("                                 * WELCOME TO ADMIN PAGE *                              ");
        int option1;
        Scanner input = new Scanner(System.in);
        while (true) {
            
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
                
                //call add_book functin
                int nxt_book;
                for(int i=0;i<200;i++) {
                nxt_book=i;
                if(book[i].Name=="empty") {
                add_book(book,nxt_book);
                 System.out.println(" DO You want to Continue? yes/no ");
                 String ans;
                    ans=input.next();
                    if(ans.equals("no")){
                        break;
                    }
                    
                }
                }
                 
            } 
            else if(option1==2)
             {
                
                 //call add_user function
                int addindx;
                 String answer;
                 for(int i=0;i<100;i++){
                     addindx=i;
                    if(reader[i].ID==0)
                    {
                    
                        add_user(reader,addindx,admin);
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
                String bookName = input.next();
                int bookIndex = searchForBook(bookName, userIndex, book);
               
                if(bookIndex==-1)
                {
                    System.out.println("NO BOOK FOUND");
                    System.out.println("_______________________________________");
                    continue;
                }
                
                 else
                {
                  System.out.println("Do you want to Remove, Rent or Return this book? (y/n)"); 
                  char choice=input.next().charAt(0);
                  if(choice=='y'||choice=='Y')
                  {
                    System.out.println("TO REMOVE THE BOOK PRESS 1");
                    System.out.println("TO RENT THE BOOK PRESS 2");
                    System.out.println("TO RETURN THE BOOK PRESS 3");
                    option1 = input.nextInt();
                    if (option1 == 1)
                    {
                      //call remove_book function
                      remove_book(book);
                      System.out.println(" DO You want to Continue? yes/no ");
                      /*String ans;
                      ans=input.next();
                      if(ans.equals("no")){
                      break;
                     *////
                    } 
                    else if (option1 == 2) 
                    {

                      Boolean isblocked = admin[userIndex].Check_isBlocked();
                      Boolean isrented = admin[userIndex].Check_isRentedBefore();
                      if (isblocked==true)
                      {
                        System.out.println("sorry, you can't rent the book because you are blocked!");
                      }
                    
                    
                      else if (isrented==true) 
                      {
                        System.out.println("sorry, you can't rent more than one book!");
                      } 
                    

                      else if(isblocked==false && isrented==false && book[bookIndex].quantity>0)
                      {
                        admin[userIndex].rent(bookName, book, bookIndex);
                      }
                
                      else
                      {
                        System.out.println("the book is not available now! Do you want to be added in the order list?(y/n)");
                        choice=input.next().charAt(0);
                        if(choice=='y')
                        {
                            //call add to order list function
                        }
                    
                      }
                    }
                   else if (option1 == 3)
                   {
                     //call return_book function
                   }
                  }
                  else if(choice=='n'||choice=='N')
                  {
                      System.out.println("_______________________________________");
                      continue;
                  }
                }
            } 
            else if (option1 == 4)
            {
                System.out.println("PLEASE ENTER MEMBER'S ID:");
                int memberID = input.nextInt();
                int memberIndex = searchForUser(memberID, reader, admin);

                if (memberIndex == -1) 
                {
                    System.out.println("NO MEMBER FOUND!");
                    continue;
                }

                System.out.println("DO YOU WANT TO REMOVE THE USER? (y/n)");
                
                char answer=input.next().charAt(0);
                if (answer=='y'||answer=='Y') 
                {
                    //call remove_user function
                    remove_user(reader, memberIndex);
                    
                    
                } 
                else if(answer=='n'||answer=='N')
                {
                    continue;
                }
                

            }
            else if (option1 == 5)
            {
                //call show_orderlist function
                System.out.println("TO ADD USER TO ORDER LIST PRESS 1");
                System.out.println("TO REMOVE USER FROM ORDER LIST PRESS 2");
                option1 = input.nextInt();
                if (option1 == 1)
                {
                    //call add to order list function  
                } else if (option1 == 2)
                {
                    //call remove from order list function
                }
            }
            else if (option1 == 6)
            {
                boolean isFound=Show_lateUsers(admin,reader);
                if(isFound==true)
                {
                System.out.println("TO BLOCK THEM PRESS 1");
                option1 = input.nextInt();
                if (option1 == 1) 
                {
                    Block(admin, reader);
                }
                }
                else if(isFound==false)
                {
                    System.out.println("The list is empty!");
                }
            } 
            else if (option1 == 7)
            {
                break;
            }

        }
    }
 public void add_book(Books[]book,int nxt_book) {
        String Name,type;
        int quantity;
        Scanner input=new Scanner(System.in);
        System.out.println("Please Enter Book's Name,Quantity and Type");
        System.out.println("Name:");
        book[nxt_book].Name=input.next();
        System.out.println("Quantity:");
        book[nxt_book].quantity=input.nextInt();
        System.out.println("Type");
        book[nxt_book].type=input.next();
        System.out.println("The Book is Successfully Added!");
    }

    public void remove_book(Books[]book) {
              int length=book.length;
              Scanner input=new Scanner(System.in);
              String Rmv_book;
              System.out.println("Please Enter Book's Name");
              Rmv_book=input.next();
              for(int i=0;i<length;i++) {
              if(book[i].Name.equals(Rmv_book) && book[i]!=null) {
              book[i]=book[length-1];
              length--;
              System.out.println("The Book is Successfully Removed!");
              break;
              }
              if(i==length-1) {
              System.out.println("This Book isn't found");
              }
              }
    }
 Scanner input2=new Scanner(System.in);
   public void add_user(Readers[]r,int indx,Librarians[] admins){
       
        System.out.println("PLEASE ENTER THE USER ID: ");
        r[indx].ID=input2.nextInt();
       
        while(true){
            int countr=0; //count to check there is a readeer id equals to another reader id
            int countad=0;  //count to check there is an admin id equals to another admin id 
            for(int i=0;i<indx;i++){
               
                if(r[indx].ID==r[i].ID){
                    countr=1;
                    break;
                }
            }
            for(int i=0;i<5;i++){
            if(r[indx].ID==admins[i].ID){
                countad=1;
                break;
            }
        }
            
            if(countr==1||countad==1){
                System.out.println("PLEASE ENTER ANOTHER ID AS IT IS ALREADY FOUND: ");
                r[indx].ID=input2.nextInt();
            }
            else if(countr==0&&countad==0){
                break;
            }
        }
        System.out.println("PLEASE ENTER THE USER EMAIL: ");
        r[indx].Email=input2.next();
        System.out.println("PLEASE ENTER THE USER PASSWORD: ");
        r[indx].password=input2.next();
        while(true){
            int count1=0;
        for(int i=0;i<indx;i++){
            String pass=r[i].password;
         if(r[indx].password.equals(pass)){
         count1=1;
         break;
         }   
        }
        if(count1==1){
            System.out.println(("PLEASE ENTER ANOTHER PAssword: "));
            r[indx].password=input2.next();
        }
        else if(count1==0){
            break;
        }
        }
        r[indx].type="READER";
        System.out.println("PLEAE ENTER THE USER FIRST NAME: ");
        r[indx].firstName=input2.next();
        System.out.println("PLEASE ENTER THE USER LAST NAME: ");
        r[indx].lastName=input2.next();
        System.out.println("PLEASE ENTER THE USER ADDRESS: ");
        r[indx].address=input2.next();
        System.out.println("PLEASE ENTER THE USER CELL PHONE : ");
        r[indx].cellPhone=input2.next();
        int length;
        
        
        while(true){
            length=r[indx].cellPhone.length();
            if(length!=11){
             System.out.println("PLEASE ENTER A CORRECT NUMBER PHONE NOT LESS THAN 11 DIGITS AND NOT MORE THAN 11: ");
             r[indx].cellPhone=input2.next();
            }
            else if(length==11){
                break;
            }
        }
        
        while(true){
            char c1='0';
        char c=r[indx].cellPhone.charAt(0);
        if(c!=c1){
         System.out.println("PLEASE ENTER ANOTHE PHONE NUMBER STARTS WITH ZERO: ");
         r[indx].cellPhone=input2.next();
        }
        else if(c==c1){
            break;
        }
        }
        r[indx].isBlocked=false;
        r[indx].Book_name=null;
        r[indx].rentDate=null;
        r[indx].Deadline_Date=null;
        
        }

               
           
        
    

     public void remove_user(Readers []r,int membindx) {
        for(int i=0;i<100;i++){
            if(i==membindx){
                if(membindx==99){
                   r[membindx].ID=0;
                   r[membindx].Email="empty";
                   r[membindx].address="empty";
                   r[membindx].cellPhone="empty";
                   r[membindx].Book_name="empty";
                   r[membindx].firstName="empty";
                   r[membindx].lastName="empty";
                   r[membindx].password="empty";
                   r[membindx].isBlocked=false;
                   r[membindx].isRent=false;
                   r[membindx].rentDate=LocalDate.of(2030, 1, 1);
                   r[membindx].Deadline_Date=LocalDate.of(2030, 1, 1);
                  break; 
                }
                else if(membindx<99){
                    for(int j=membindx;j<100;j++){
                   if(j+1<99){     
                  
                   r[j]=r[j+1];
                    }
                    }
                    break;
             }
            }
        }
        
        System.out.println("Removal is successfully completed");
    }
   
   
        public void show_orderlist(String bookName, int userIndex, Readers[] reader, Librarians[] admin, Books[] book) {
        int bookIndex = -1;
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                bookIndex = i;
                break;
            }
        }
        
        if (bookIndex == -1) {
            System.out.println("BOOK IS NOT FOUND!");
        }
        
        for (int i = 0; i < book[bookIndex].counter; i++)
        {
            System.out.println("Info of user no. #" + (i+1) + " in the Order List:");
            int current = book[bookIndex].orderList[i]; 
            for (int j = 0; j < 100; j++) 
            {
                if (current == reader[j].ID)
                {
                    System.out.println("Member's ID: " + reader[j].ID + " (Reader)");
                    System.out.println("Member's Name: " + reader[j].firstName + " " + reader[j].lastName);
                    System.out.println("Member's Email: " + reader[j].Email);
                    break;
                }

                if (j < 5) 
                {
                    if (current == admin[j].ID) {
                        System.out.println("Member's ID: " + admin[j].ID + " (Admin)");
                        System.out.println("Member's Name: " + admin[j].firstName + " " + admin[j].lastName);
                        System.out.println("Member's Email: " + admin[j].Email);
                        break;
                    }
                }

            }
            
        }
    }

    public void add_user_orderlist(String bookName, int User_ID, Books[] book) {
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                book[i].orderList[book[i].counter] = User_ID;
                book[i].counter++;
                break;
            }
        }
    }

    public void remove_user_orderlist(String bookName, int User_ID, Books[] book) {
        int bookIndex = -1;
        for (int i = 0; i < 200; i++) {
            if (bookName.equals(book[i].Name)) {
                bookIndex = i;
                for (int j = 1; j < 200; j++) {
                    book[i].orderList[j - 1] = book[i].orderList[j];
                }
                book[i].counter--;
                break;
            }
        }
        if (bookIndex == -1) {
            System.out.println("Book is not found in the system.");
        }
    }

   
    @Override
    public void rent(String name, Books[] book, int bookIndex) 
    {

        
        isRent=true;
        Book_name=name;
        rentDate=LocalDate.now();
        LocalDate deadline=rentDate.plusDays(7);
        Deadline_Date=deadline;
        book[bookIndex].quantity--;
        System.out.println("you have successfully rented the book! you must return it before "  +Deadline_Date);
        
      
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
    
    public void Return_book(Books[] b,int book_indx,Readers[]r,int user_indx)
    {
        b[book_indx].quantity++;
        r[user_indx].isRent=false;
        r[user_indx].rentDate=LocalDate.of(2030,1,2);
        r[user_indx].Deadline_Date=LocalDate.of(2030,1,2);
        
    }
       
        
    

    public boolean Show_lateUsers(Librarians[] admin,Readers[] reader)
    {
        boolean isFound=false;
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 100; i++)
        {
            if(i<5)
            {
            if (admin[i].Deadline_Date.compareTo(now) < 0)
            {
                System.out.println(admin[i].Email);
                isFound=true;
            }
            }
             if (reader[i].Deadline_Date.compareTo(now) < 0)
            {
                System.out.println(reader[i].Email);
                isFound=true;
            }

        }
        return isFound;
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
                    admin[i].isRent=false;
                    admin[i].Book_name="empty";
                    admin[i].Deadline_Date=LocalDate.of(2030,1,1);
                    admin[i].rentDate=LocalDate.of(2030,1,1);
                }
            }
            if (reader[i].Deadline_Date.compareTo(now) < 0)
            {
                reader[i].isBlocked = true;
                reader[i].isRent=false;
                reader[i].Book_name="empty";
                reader[i].Deadline_Date=LocalDate.of(2030,1,1);
                reader[i].rentDate=LocalDate.of(2030,1,1);
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
            } 
            if(i<5)
            {
            if(memberID==admin[i].ID)
            {
                memberIndex=i;
                System.out.println("Member's ID: "+admin[i].ID+"   (Admin)");
                System.out.println("Member's Name: "+admin[i].firstName+" "+admin[i].lastName);
                System.out.println("Member's Email: "+admin[i].Email);
                System.out.println("Member's Address: "+admin[i].address);
                System.out.println("Member's Phone Number: "+admin[i].cellPhone);
                System.out.println("Member's Status:");
                if(admin[i].isBlocked==true)
                {
                    System.out.println("\t"+"Blocked");
                }
                else
                {
                    System.out.println("\t"+"Not Blocked");
                }
                
                if(admin[i].isRent==true)
                {
                    System.out.println("\t"+"Already Rented a Book (Book Name: "+admin[i].Book_name+", Rent Date: "+admin[i].rentDate+", Deadline: "+admin[i].Deadline_Date+") ");
                }
                else
                {
                    System.out.println("\t"+"Not Rented a Book");
                }
                
                break;
            }
            
            
        }
        }
        return memberIndex;
    }
}
