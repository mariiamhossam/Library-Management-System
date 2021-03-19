/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library.users;

import Library.Books;
import Library.users.Readers;
import java.util.Scanner;
import java.time.*;
import java.util.Optional;

/**
 *
 * @author yosef
 */
public class Librarians extends Users {

    Scanner input = new Scanner(System.in);

    
    public Librarians() {
    }

    public Librarians(int ID, String Email, String password, String type, String firstName, String lastName,
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

    public void Admin_page(int userIndex, Readers[] reader, Librarians[] admin, Books[] book) {
        System.out.println("\t\t\t\t* WELCOME TO ADMIN PAGE *\t\t\t\t");
        int option1;
        //Scanner input = new Scanner(System.in);
        while (true) {

            displayMenu();
            option1 = input.nextInt();
            if (option1 == 1) {

                //call add_book functin
                int nxt_book;
                for (int i = 0; i < 200; i++) {
                    nxt_book = i;
                    if (book[i].Name == "empty") {
                        add_book(book, nxt_book);
                        System.out.println(" DO You want to Continue? yes/no ");
                        String ans;
                        ans = input.next();
                        if (ans.equals("no")) {
                            break;
                        }

                    }
                }

            } else if (option1 == 2) {

                //call add_user function
                int addindx;
                String answer;
                for (int i = 0; i < 100; i++) {
                    addindx = i;
                    if (reader[i].getID() == 0) {

                        add_user(reader, addindx, admin);
                        System.out.println("DO YOU WANT TO CONTINUE: yes/no");
                        answer = input2.next();
                        if (answer.equals("no")) {
                            break;
                        }
                    }

                }

            } else if (option1 == 3) {
                 System.out.println("PLEASE ENTER BOOK NAME:");
                String bookName = input.next();
                int bookIndex = searchForBook(bookName, book);
               
                if (bookIndex == -1) {
                    System.out.println("NO BOOK IS FOUND");
                    System.out.println("_______________________________________");
                    continue;
                } else {
                  System.out.println("Book Name: " + book[bookIndex].Name + "\t" + "Type: " + book[bookIndex].type + "\t\t" + "Quantity: " + book[bookIndex].quantity);
                System.out.println("_______________________________________"); 
                    System.out.println("Do you want to Remove, Rent or Return this book? (y/n)");
                    char choice = input.next().charAt(0);
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println("TO REMOVE THE BOOK PRESS 1");
                        System.out.println("TO RENT THE BOOK PRESS 2");
                        if(admin[userIndex].Book_name.equals(book[bookIndex].Name)){
                          System.out.println("TO RETURN THE BOOK PRESS 3");
                        }
                        option1 = input.nextInt();
                        if (option1 == 1) {
                            //call remove_book function
                            remove_book(book,bookIndex);
                            

                             
                        } else if (option1 == 2) {

                            Boolean isblocked = admin[userIndex].Check_isBlocked();
                            Boolean isrented = admin[userIndex].Check_isRentedBefore();
                            if (isblocked == true) {
                                System.out.println("Sorry, you can't rent the book because you are blocked!");
                            } else if (isrented == true) {
                                System.out.println("Sorry, you can't rent more than one book!");
                            } else if (isblocked == false && isrented == false && book[bookIndex].quantity > 0) {
                                admin[userIndex].rent(bookName, book, bookIndex);
                            } else {
                                System.out.println("The book is not available now! Do you want to be added in the order list?(y/n)");
                                choice = input.next().charAt(0);
                                if (choice == 'y' || choice == 'Y') {
                                    //call add to order list function
                                    bookIndex = searchForBook(bookName, book);
                                    int userID = admin[userIndex].getID();
                                    add_user_to_orderlist(userID, book, bookIndex);
                                    
                                }

                            }
                        } else if (option1 == 3) {
                            //call return_book function
                            admin[userIndex].Return_book(book, bookIndex);
                            update_orderlist(book, reader, admin);
                            
                        }
                    } else if (choice == 'n' || choice == 'N') {
                        System.out.println("_______________________________________");
                        continue;
                    }
                }
            } else if (option1 == 4) {
                System.out.println("PLEASE ENTER MEMBER'S ID:");
                int memberID = input.nextInt();
                int memberIndex = searchForUser(memberID, reader, admin);

                if (memberIndex == -1) {
                    System.out.println("NO MEMBER FOUND!");
                    System.out.println("_______________________________________"); 
                    continue;
                }
                
                else{
                    System.out.println("Member's ID: " + reader[memberIndex].getID());
                System.out.println("Member's Name: " + reader[memberIndex].firstName + " " + reader[memberIndex].lastName);
                System.out.println("Member's Email: " + reader[memberIndex].Email);
                System.out.println("Member's Address: " + reader[memberIndex].address);
                System.out.println("Member's Phone Number: " + reader[memberIndex].getCellPhone());
                System.out.println("Member's Status:");
                if (reader[memberIndex].isBlocked == true) {
                    System.out.println("\t" + "Blocked");
                } else {
                    System.out.println("\t" + "Not Blocked");
                }
                if (reader[memberIndex].isRent == true) {
                    System.out.println("\t" + "Already Rented a Book (Book Name: " + reader[memberIndex].Book_name + ", Rent Date: " + reader[memberIndex].rentDate + ", Deadline: " + reader[memberIndex].Deadline_Date + ") ");
                } else {
                    System.out.println("\t" + "Not Rented a Book");
                    System.out.println("_______________________________________");
                }
                }
                

                System.out.println("DO YOU WANT TO REMOVE THE USER? (y/n)");

                char answer = input.next().charAt(0);
                if (answer == 'y' || answer == 'Y') {
                    //call remove_user function
                    remove_user(reader, memberIndex);

                } else if (answer == 'n' || answer == 'N') {
                    continue;
                }

            } else if (option1 == 5) {
                //call show_orderlist function
                System.out.println("PLEASE ENTER BOOK NAME TO SHOW ITS ORDER LIST:");
                String bookName = input.next();

                int bookIndex = searchForBook(bookName, book);

                boolean empty=show_orderlist(reader, admin, book, bookIndex);

                System.out.println("TO ADD USER TO ORDER LIST PRESS 1");
                if(empty!=true){
                System.out.println("TO REMOVE USER FROM ORDER LIST PRESS 2");
                }
                System.out.println("TO RETURN TO THE MENU PRESS 3");
                option1 = input.nextInt();
                if (option1 != 3) {
                    System.out.println("TO DO THIS OPERATION ON YOURSELF PRESS 1");
                    System.out.println("TO DO THIS OPERATION ON ANOTHER USER PRESS 2");

                    int choice = input.nextInt();

                    int userID = admin[userIndex].getID();
                    if (choice == 2) {
                        System.out.println("PLEASE ENTER USER'S ID");
                        userID = input.nextInt();
                    }

                    if (option1 == 1) {
                        //call add to order list function
                        add_user_to_orderlist(userID, book, bookIndex);
                    } else if (option1 == 2) {
                        //call remove from order list function
                        remove_user_orderlist(userID, book, bookIndex);
                    }
                } else if (option1 == 3) {
                    continue;
                }
            } else if (option1 == 6) {
                boolean isFound = Show_lateUsers(admin, reader);
                if (isFound == true) {
                    System.out.println("TO BLOCK THEM PRESS 1");
                    option1 = input.nextInt();
                    if (option1 == 1) {
                        Block(admin, reader);
                        System.out.println("Blocking process is successfully done");
                        System.out.println("_______________________________________");
                    }
                } else if (isFound == false) {
                    System.out.println("THE LIST IS EMPTY!");
                    System.out.println("_______________________________________"); 
                }
            } else if (option1 == 7) {
                break;
            }

        }
    }

    public void displayMenu() {
        System.out.println("TO ADD A BOOK PRESS 1 ");
        System.out.println("TO ADD A USER PRESS 2 ");
        System.out.println("TO SEARCH FOR A BOOK PRESS 3 ");
        System.out.println("TO SEARCH FOR A MEMBER PRESS 4 ");
        System.out.println("TO SHOW ORDER LIST PRESS 5");
        System.out.println("TO SHOW LATE USERS PRESS 6");
        System.out.println("TO LOG OUT PRESS 7");
    }

    public void add_book(Books[] book, int nxt_book) {
        String Name, type;
        int quantity;
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Book's Name,Quantity and Type");
        System.out.println("Name:");
        book[nxt_book].Name = input.next();
        System.out.println("Quantity:");
        book[nxt_book].quantity = input.nextInt();
        System.out.println("Type");
        book[nxt_book].type = input.next();
        System.out.println("The Book is Successfully Added!");
    }
    
//     public void remove_book(Books[] book,int bookIndex) 
//    {
//        
//        for (int i = bookIndex; i < 200-1; i++) 
//        {
//            if(book[i].Name=="empty")
//            {
//               break;
//            }
//            else
//            {
//               book[i] = book[i+1];
//            }
//        }
//        
//        System.out.println("The Book is Successfully Removed!");
//        System.out.println("_______________________________________");
//       
//    }

         public void remove_book(Books[] book,int bookIndex) {
         String Rmv_book=book[bookIndex].Name;
      for(int i=0;i<book.length;i++) {
      if(book[i].Name.equals(Rmv_book)) {
      book[i].Name="empty";
          book[i].quantity=0;
          book[i].type="empty";
          book[i].counter=0; 
          System.out.println("The Book Successfully Removed!");
          break;
      } 
    }  //shifting the empty index
      for(int i=0;i<200;i++) {
      if(book[i].Name.equals("empty")) {
          for(int j=i;j<199;j++)
          book[j]=book[j+1]; }
      }

    }

    Scanner input2 = new Scanner(System.in);

    public void add_user(Readers[] r, int indx, Librarians[] admins) {
        

        System.out.println("PLEASE ENTER THE USER ID: ");
        r[indx].setID(input2.nextInt());

        while (true) {
            int countr = 0; //count to check there is a readeer id equals to another reader id
            int countad = 0;  //count to check there is an admin id equals to another admin id 
            for (int i = 0; i < indx; i++) {

                if (r[indx].getID() == r[i].getID()) {
                    countr = 1;
                    break;
                }
            }
            for (int i = 0; i < 5; i++) {
                if (r[indx].getID() == admins[i].getID()) {
                    countad = 1;
                    break;
                }
            }

            if (countr == 1 || countad == 1) {
                System.out.println("PLEASE ENTER ANOTHER ID AS IT IS ALREADY FOUND: ");
                r[indx].setID(input2.nextInt());
            } else if (countr == 0 && countad == 0) {
                break;
            }
        }
        System.out.println("PLEASE ENTER THE USER EMAIL: ");
        r[indx].Email = input2.next();
        System.out.println("PLEASE ENTER THE USER PASSWORD: ");
        r[indx].setPassword(input2.next());
        while (true) {
            int count1 = 0;
            for (int i = 0; i < indx; i++) {
                String pass = r[i].getPassword();
                if (r[indx].getPassword().equals(pass)) {
                    count1 = 1;
                    break;
                }
            }
            if (count1 == 1) {
                System.out.println(("PLEASE ENTER ANOTHER PAssword: "));
                r[indx].setPassword(input2.next());
            } else if (count1 == 0) {
                break;
            }
        }
        r[indx].type = "READER";
        System.out.println("PLEAE ENTER THE USER FIRST NAME: ");
        r[indx].firstName = input2.next();
        System.out.println("PLEASE ENTER THE USER LAST NAME: ");
        r[indx].lastName = input2.next();
        System.out.println("PLEASE ENTER THE USER ADDRESS: ");
        r[indx].address = input2.next();
        System.out.println("PLEASE ENTER THE USER CELL PHONE : ");
        r[indx].setCellPhone(input2.next());
        int length;

        while (true) {
            length = r[indx].getCellPhone().length();
            if (length != 11) {
                System.out.println("PLEASE ENTER A CORRECT NUMBER PHONE NOT LESS THAN 11 DIGITS AND NOT MORE THAN 11: ");
                r[indx].setCellPhone(input2.next());
            } else if (length == 11) {
                break;
            }
        }

        while (true) {
            char c1 = '0';
            char c = r[indx].getCellPhone().charAt(0);
            if (c != c1) {
                System.out.println("PLEASE ENTER ANOTHE PHONE NUMBER STARTS WITH ZERO: ");
                r[indx].setCellPhone(input2.next());
            } else if (c == c1) {
                break;
            }
        }
        r[indx].isBlocked = false;
        r[indx].Book_name = "empty";
        r[indx].rentDate = LocalDate.of(2030, 1, 1);
        r[indx].Deadline_Date = LocalDate.of(2030, 1, 1);

    }

    public void remove_user(Readers[] r, int membindx) {
        for (int i = 0; i < 100; i++) {
            if (i == membindx) {
                if (membindx == 99) {
                    r[membindx].setID(0); 
                    r[membindx].Email = "empty";
                    r[membindx].address = "empty";
                    r[membindx].setCellPhone("empty");
                    r[membindx].Book_name = "empty";
                    r[membindx].firstName = "empty";
                    r[membindx].lastName = "empty";
                    r[membindx].setPassword("empty"); 
                    r[membindx].isBlocked = false;
                    r[membindx].isRent = false;
                    r[membindx].rentDate = LocalDate.of(2030, 1, 1);
                    r[membindx].Deadline_Date = LocalDate.of(2030, 1, 1);
                    break;
                } else if (membindx < 99) {
                    for (int j = membindx; j < 100; j++) {
                        if (j + 1 < 99) {

                            r[j] = r[j + 1];
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("Removal is successfully done");
        System.out.println("_______________________________________");
    }

    
    public boolean show_orderlist(Readers[] reader, Librarians[] admin, Books[] book, int bookIndex) {
        boolean empty=false;
        if (book[bookIndex].counter == 0) {
            empty=true;
            System.out.println("The book " + book[bookIndex].Name + " has no users in it's orderlist.");
            System.out.println("_______________________________________"); 
            //return;
        }
        else{
        for (int i = 0; i < book[bookIndex].counter; i++) {
            System.out.println("Info of user no. #" + (i + 1) + " in the Order List:");
            int current = book[bookIndex].orderList[i];
            for (int j = 0; j < 100; j++) {
                if (current == reader[j].getID()) {
                    System.out.println("Member's ID: " + reader[j].getID() + " (Reader)");
                    System.out.println("Member's Name: " + reader[j].firstName + " " + reader[j].lastName);
                    System.out.println("Member's Email: " + reader[j].Email);
                    System.out.println("--------------------------------");
                    break;
                }

                if (j < 5) {
                    if (current == admin[j].getID()) {
                        System.out.println("Member's ID: " + admin[j].getID() + " (Admin)");
                        System.out.println("Member's Name: " + admin[j].firstName + " " + admin[j].lastName);
                        System.out.println("Member's Email: " + admin[j].Email);
                        System.out.println("--------------------------------");
                        break;
                    }
                }

            }

        }
    }
        return empty;
    }

    

    public void remove_user_orderlist(int User_ID, Books[] book, int bookIndex) {
        int index = -1;
        for (int i = 0; i < 200; i++) {
            if (book[bookIndex].orderList[i] == User_ID) {
                index = i;
                System.out.println("Removal is successfully done");
                System.out.println("_______________________________________");
                break;
            }
        }

        if (index == -1) {
            System.out.println("This user is not in the orderlist.");
            System.out.println("Would you like to remove another user? (y/n)");
            char choice = input.next().charAt(0);
            if (choice == 'y' || choice == 'Y') {
                System.out.println("Please enter the user's ID: ");
                User_ID = input.nextInt();
                remove_user_orderlist(User_ID, book, bookIndex);
                return;
            }
        }

        if (index == 199) {
            book[bookIndex].orderList[199] = 0;
        }
        
        for (int i = index+1; i < 199; i++) {
            book[bookIndex].orderList[i - 1] = book[bookIndex].orderList[i];
            book[bookIndex].counter--;
        }
        

    }

    public boolean Show_lateUsers(Librarians[] admin, Readers[] reader) {
        boolean isFound = false;
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 100; i++) {
            if (i < 5) {
                if (admin[i].Deadline_Date.compareTo(now) < 0) {
                    System.out.println(admin[i].Email);
                    isFound = true;
                }
            }
            if (reader[i].Deadline_Date.compareTo(now) < 0) {
                System.out.println(reader[i].Email);
                isFound = true;
            }

        }
        return isFound;
    }

    public void Block(Librarians[] admin, Readers[] reader) {
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 100; i++) {
            if (i < 5) {
                if (admin[i].Deadline_Date.compareTo(now) < 0) {
                    admin[i].isBlocked = true;
                    admin[i].isRent = false;
                    admin[i].Book_name = "empty";
                    admin[i].Deadline_Date = LocalDate.of(2030, 1, 1);
                    admin[i].rentDate = LocalDate.of(2030, 1, 1);
                }
            }
            if (reader[i].Deadline_Date.compareTo(now) < 0) {
                reader[i].isBlocked = true;
                reader[i].isRent = false;
                reader[i].Book_name = "empty";
                reader[i].Deadline_Date = LocalDate.of(2030, 1, 1);
                reader[i].rentDate = LocalDate.of(2030, 1, 1);
            }
        }
    }
    
    

   
}
