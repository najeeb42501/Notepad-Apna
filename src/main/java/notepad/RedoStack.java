/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

/**
 *
 * @author nbang
 */
public class RedoStack {
    
    
    Node head;
    Node top;
  
    
    public RedoStack() {
        
        this.head = null;
        this.top = null;
        
    }
    
      public void push(String t){
        
        Node n = new Node(t);
        
         
        
        if(head == null){
            top = n;
            head = n;
            System.out.println("Inserting : " + t);
       }else{
            n.next = top ;
            top = n;
            System.out.println("Inserting : " + t);

        }
    }
    
    public void pop(){
        
        if (top == null) {
            System.out.print("\nStack Underflow");
            return;
        }
        
        top = top.next;
    }
    
       public String peek()
    {
        // check for empty stack
        if (!isEmpty()) {
            return top.data;
        }
        else {
            System.out.println("Stack is empty");
            return "";
        }
    }
       
     public boolean isEmpty(){
        
        if(top==null)
            return true;
        else
            return false;
    }
    
    
}
