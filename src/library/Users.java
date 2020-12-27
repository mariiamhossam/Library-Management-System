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
    
    
    
    public abstract int searchForBook(String bookName,int index,Books[]book);
    public abstract int searchForUser(int memberID,Readers[] reader,Librarians[] admin);
    public abstract void rent(String name,Books[]book,int bookIndex);
    public abstract Boolean Check_isBlocked();
    public abstract boolean Check_isRentedBefore();
    public abstract void Return();
}
