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
    public String orderList[]=new String[quantity];
    public Books(){
        
    }
    
    public Books(String Name,int quantity,String type,String orderlist[])
    {
        this.Name=Name;
        this.quantity=quantity;
        this.type=type;
        this.orderList=orderlist;
    }
}