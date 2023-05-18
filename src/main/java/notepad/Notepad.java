/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author nbang
 */




public class Notepad extends JFrame implements ActionListener,DocumentListener{

   static Stack stack = new Stack();
    
    
    @Override
    public void insertUpdate(DocumentEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       // textArea.setText("Hello1");
       
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        //textArea.setText("Hello2");
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //   textArea.setText("Hello3");
        
        
    }

    
    
    
    
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter  {
    
    public MyHighlightPainter(Color color){
        super(color);
    }
}
    
    Highlighter.HighlightPainter painter = new MyHighlightPainter(Color.yellow);
    
    
    public void removeHighlighter(JTextComponent comp){
        
        Highlighter h = comp.getHighlighter();
        Highlighter.Highlight[] hs = h.getHighlights();
        
        for(int i=0 ; i<hs.length; i++){
            
            if(hs[i].getPainter()instanceof MyHighlightPainter){
                h.removeHighlight(hs[i]);
            }
            
        }
    }
    public void highlight(JTextComponent comp,String pattern){
        
        removeHighlighter(comp);
        
        try{
            Highlighter h = comp.getHighlighter();
            Document doc = comp.getDocument();
            String text = doc.getText(0,doc.getLength());
            int pos = 0;
            
            while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0){
                
                h.addHighlight(pos, pos+ pattern.length(), painter);
                pos+= pattern.length();
             
            }
                       
        }catch(Exception e){
            
        }
    }

    
    JMenuBar menu = new JMenuBar();
    
    JMenu file = new JMenu("File");
    JMenu options = new JMenu("Options");
    JMenu team = new JMenu("Team");
    
    JMenuItem newFile = new JMenuItem("New File");
    JMenuItem openFile = new JMenuItem("Open File");
    JMenuItem saveFile = new JMenuItem("Save File");
    JMenuItem editFile = new JMenuItem("Edit File");
    
    JMenuItem undo = new JMenuItem("Undo");
    JMenuItem redo = new JMenuItem("Redo");
    JMenuItem find = new JMenuItem("Find");
    JMenuItem replace = new JMenuItem("Replace");
    
    JMenuItem m = new JMenuItem("Group Members");
    
    static JTextArea textArea = new JTextArea();

    
    
    

    
    Notepad(){
        setBounds(200,100,600,600);
        setTitle("Apna Notepad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(menu);
        
        setJMenuBar(menu);
        
        menu.add(file);
        menu.add(options);
        menu.add(team);
        
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(editFile);
        
        options.add(undo);
        options.add(redo);
        options.add(find);
        options.add(replace);
        
        team.add(m);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        editFile.addActionListener(this);
        openFile.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        find.addActionListener(this);
        replace.addActionListener(this);
        m.addActionListener(this);
       
        
      //  textArea.addKeyListener(this);
      
    //    textArea.getDocument().addDocumentListener();
        textArea.setEditable(false);
    }
    
   
    public static void main(String[] args){
        
        new Notepad().setVisible(true);


      
             DocumentListener documentListener = new DocumentListener() {
              public void changedUpdate(DocumentEvent documentEvent) {
                  // printIt(documentEvent);
                     System.out.println("ChangesUpdate");
                     System.out.println(textArea.getText());
      }
      public void insertUpdate(DocumentEvent documentEvent) {
      //  printIt(documentEvent);
      
        System.out.println("insertUpdate");
           System.out.println(textArea.getText());
        //   stack.push(textArea.getText());
           
        stack.undo(textArea.getText());
           
           
      }
      public void removeUpdate(DocumentEvent documentEvent) {
        //printIt(documentEvent);
        
        System.out.println("rempveUpdate");
        
       
       
      }
      
     
    };
    textArea.getDocument().addDocumentListener(documentListener);
            
            
          
        
        
    }
    
    
   
   
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       
        
        if(e.getActionCommand().equalsIgnoreCase("New File")){
            
            textArea.setText(null);
            
        }else if(e.getActionCommand().equalsIgnoreCase("Open File")){
          
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only Text File","txt");
            file.setAcceptAllFileFilterUsed(false);
            file.addChoosableFileFilter(filter);
            
            int action = file.showSaveDialog(null);
            
          
            if(action!= JFileChooser.APPROVE_OPTION){
                return;
            }else{
               
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file.getSelectedFile()));
                    textArea.read(reader,null);
                    
                } catch (IOException ex) {
                   ex.printStackTrace();
                }
                
                    
            }
                
            
        }else if(e.getActionCommand().equalsIgnoreCase("Save File")){
            
              
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only Text File","txt");
            file.setAcceptAllFileFilterUsed(false);
            file.addChoosableFileFilter(filter);
            
            int action = file.showSaveDialog(null);
            
            if(action!= JFileChooser.APPROVE_OPTION){
                return;
                
            }else{
                
                String filename = file.getSelectedFile().getAbsolutePath().toString();
                
                if(!filename.contains(".txt")){
                    filename+=".txt";
                }
                                
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    textArea.write(writer);                   
                } catch (IOException ex) {                    
                    ex.printStackTrace();                
                }                   
            }
            
        }else if(e.getActionCommand().equalsIgnoreCase("Edit File")){
            
            textArea.setEditable(true);
            
        }else if(e.getActionCommand().equalsIgnoreCase("Redo")){
            
           System.out.println("Undo stack peek : " + stack.redoStack.peek()); 
            textArea.setText(stack.redoStack.peek());
          stack.redoStack.pop();
             
            
        }else if(e.getActionCommand().equalsIgnoreCase("Undo")){
           
           stack.undoStack.pop();
           System.out.println("Undo stack peek : " + stack.undoStack.peek());
           textArea.setText(stack.undoStack.peek());
           stack.redo(stack.undoStack.peek());
          stack.undoStack.pop();
          
         //    System.out.println(stack.pop());
            
            
            
            
            
            
        }else if(e.getActionCommand().equalsIgnoreCase("Find")){
            
            String s = (String) JOptionPane.showInputDialog(null, "Enter word to find...", "Find", JOptionPane.PLAIN_MESSAGE, null, null, "");

            
             
            LinkedList l = new LinkedList();
            
            l.saparateWords(textArea.getText());
            
     
            
           if(l.find(s)){
              highlight(textArea, s);
              // System.out.println("founddddddd");
           }
           
            
          //  l.printAll();
            
        }else if(e.getActionCommand().equalsIgnoreCase("Replace")){
            
            JTextField t1 = new JTextField();
            JTextField t2 = new JTextField();
    
            Object[] fields = {
                "Word :", t1,
                "Replace with :", t2
            };    
            
            JOptionPane.showConfirmDialog(null, fields, "Replace Words : ", JOptionPane.OK_CANCEL_OPTION);

            LinkedList r = new LinkedList();

            String text = r.replaceWords(textArea.getText(),t1.getText(),t2.getText());
            
       
           
         
        textArea.setText(text);
        text = "";
          

            
            
        }else if(e.getActionCommand().equalsIgnoreCase("Group Members")){
            
            textArea.setText(" Group Members : \n" + "Najeebullah Khan (023-20-0058) \n Muhammad Dau (023-20-0092) \n Muhammad Faizan (023-20-0093)");
        }
    }
    
    
    
    
}
