/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

/**
 *
 * @author yosef
 */
public class Books {
     public String Name;
    public int quantity;
    public String type;
    public int orderList[] = new int[200];
    public int counter = 0;  //Counter for number of users in orderList 

    public Books(){
        
    }
    
    public Books(String Name,int quantity,String type,int orderlist[])
    {
        this.Name=Name;
        this.quantity=quantity;
        this.type=type;
        this.orderList=orderlist;
    }
}
