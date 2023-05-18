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



public class Stack {
    
    Node head;
    Node top;
    
    UndoStack undoStack;
    RedoStack redoStack;
    
    public Stack() {
        
        this.head = null;
        this.top = null;
        undoStack = new UndoStack();
        redoStack = new RedoStack();
        
    }
    
    
       
   
    
    public void undo(String text){
        
        undoStack.push(text);
        System.out.println("Push in undostack = " + text);

       // return text;
    }
    
    public void redo(String text) {
        
        redoStack.push(text);
        System.out.println("Push in redostack = " + text);

      
       
    }
    
  
}
