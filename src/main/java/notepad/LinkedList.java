/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;
import notepad.Node;
/**
 *
 * @author nbang
 */
/*
class Node{
        
        String data;
        Node next;
       // Node prev;
        
        Node(String data){
            this.data = data;
        }
    }

*/
public class LinkedList {
    
    Node head;
    
    LinkedList(){
        
        head = null;
    }
        
    
    public void insert(String data) {
        
        Node node = new Node(data);
        
        if(head == null){
            head= node;
        }else{
            
            Node c = head;
            
            while(c.next !=null){               
                c = c.next;                    
            }
            c.next = node;
            //node.prev = c;
        }
     
    }
    
    public void printAll(){
        
        Node c = head;
        
        while(c !=null){
            
            System.out.print(c.data + " ");
            c = c.next;
            
        }
    }
    public boolean find(String text){
        
        Node c = head;

        while (c != null) {
            
            if (c.data.contains(text)) {
             //   System.out.print("Found");
                return true;
            }
            c = c.next;

        }

        return false;
        
    }
    
    void saparateWords(String text){
           
        for(String s: text.split(" ")){
            if(s.contains("\r\n")){
                
            }else
            insert(s);
        }
    }
    
    
    public String replaceWords(String text,String to,String with){
           String t ="";
        for(String s: text.split(" ")){
            
            
            if(s.equalsIgnoreCase(to)){
                //System.out.println("replace");
                insert(with);
                t+= with + " ";
            }else{
                insert(s);
                t+= s + " ";
            }
           // t+= s + " ";
            
        }
        return t;
    }
    
    public static void main(String[] args) {
        
    }
    
}
