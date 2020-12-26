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
    
    
    
    public abstract void searchForBook();
    public abstract void searchForUser();
    public abstract void rent(String name,Books[]book,int index);
}