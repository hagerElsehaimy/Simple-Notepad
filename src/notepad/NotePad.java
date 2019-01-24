/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author hagar
 */
public class NotePad extends Application {
    
    
        MenuItem new_file;
        MenuItem open_file;
        MenuItem save_file;
        MenuItem exit;
        Menu file;
        MenuItem undo ;
        MenuItem cut;
        MenuItem paste;
        MenuItem copy;
        MenuItem delete;
        MenuItem select_all;
        Menu  edit;
        MenuItem about;
        Menu help;
        BorderPane root;
        Scene scene;
        MenuBar menubar;
        SeparatorMenuItem exit_sep;
        SeparatorMenuItem undo_sep;
        SeparatorMenuItem selectAll_sep;
        TextArea editor;
        FileChooser fileChooser = new FileChooser();
        Desktop desktop = Desktop.getDesktop();
        Stage stg;
    
    
    @Override
    public void init ()
    {
        
        this.exit_sep=new SeparatorMenuItem();
        this.undo_sep = new SeparatorMenuItem();
        this.selectAll_sep=new SeparatorMenuItem();
        
        
        
        new_file = new MenuItem("new");
        this.new_file.setAccelerator(KeyCombination.keyCombination("Alt+n"));
        
        open_file = new MenuItem("open");
        this.open_file.setAccelerator(KeyCombination.keyCombination("Alt+o"));
        
        save_file = new MenuItem("save");
        this.save_file.setAccelerator(KeyCombination.keyCombination("Alt+s"));
        
        exit = new MenuItem("exit");
        this.exit.setAccelerator(KeyCombination.keyCombination("Alt+e"));
        
        file = new Menu("File");
       
        
        
        undo = new MenuItem("undo");
        this.undo.setAccelerator(KeyCombination.keyCombination("Alt+u"));
        
        cut = new MenuItem("cut");
        this.cut.setAccelerator(KeyCombination.keyCombination("Alt+x")); 
        
        copy = new MenuItem("copy");
        this.copy.setAccelerator(KeyCombination.keyCombination("Alt+c"));
        
        paste = new MenuItem("paste");
        this.paste.setAccelerator(KeyCombination.keyCombination("Alt+p"));
        
        delete = new MenuItem("delete");
        this.delete.setAccelerator(KeyCombination.keyCombination("Alt+d"));
        
        select_all = new MenuItem("select all");
        this.select_all.setAccelerator(KeyCombination.keyCombination("Alt+a"));        
        edit = new Menu("Edit");
        
        about = new MenuItem("about");
        this.about.setAccelerator(KeyCombination.keyCombination("Alt+f1"));
        
        help = new Menu("Help");
        
        this.editor = new TextArea();
   
        root = new BorderPane();
        
        menubar= new MenuBar();
        
        
        this.file.getItems().addAll(this.new_file,this.exit_sep,this.open_file,this.save_file,this.exit);
        this.edit.getItems().addAll(this.undo,this.undo_sep,this.cut,this.copy,this.delete,this.paste,this.selectAll_sep,this.select_all);
        this.help.getItems().add(this.about);
        
        this.menubar.getMenus().addAll(this.file,this.edit,this.help);
       
        this.root.setTop(this.menubar);
        this.root.setCenter(this.editor);
        
        this.scene = new Scene(root, 800, 650);
        
//=================================Event handling===================================================
    
    this.new_file.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                NotePad.this.editor.clear();
            }
        });
    
    this.exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                    System.exit(0);
                            }
        });
    
    this.undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                NotePad.this.editor.undo();
            }
        });
    
    this.copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                NotePad.this.editor.copy();
            }
        });
    
    this.paste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                NotePad.this.editor.paste();
            }
        });
    
    this.cut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
               NotePad.this.editor.cut();           
            }
        });
    
    this.delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                NotePad.this.editor.deleteNextChar();
            }
        });
    
    this.select_all.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                NotePad.this.editor.selectAll();
            }
        });
    
    this.about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CopyRight");
                alert.setHeaderText(null);
                alert.setContentText("creator: hager mohamed\n Track: cloud platfrom development");
                alert.showAndWait();
            }
        });
}
    
    @Override
    public void start(Stage primaryStage) 
    {
        this.open_file.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event)
            {
                
                File file = fileChooser.showOpenDialog(primaryStage); 
                
                if (file != null) 
                {
                    try
                    {
                       openFile(file); 
                    }
                    catch(Exception e)
                    {
                        
                    }
                }
                else
                {
                    System.out.print("error!");
                }
                
            }
        });
    
        primaryStage.setTitle("Fx NotePad");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //===============================================================
        
        this.save_file.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
               
                File file = fileChooser.showSaveDialog(primaryStage);
                
                
                if (file != null) 
                {
                    try
                    {
                       saveFile(file); 
                    }
                    catch(Exception e)
                    {
                        
                    }
                }
                
                
              
        }
   });
    }

    /**
     * @param args the command line arguments
     */
    public void openFile(File file)
    {
        FileReader br; 
                try 
                {
                    br = new FileReader(file);
                    char[] c=new char[(int)file.length()];
                    String st; 
                    br.read(c);
                    st=new String(c);
                    //while ((st = br.read(c)) != null) 
                    NotePad.this.editor.setText(st);
                } 
                catch (Exception ex)
                {
                    System.out.print(ex);
                }
    }
    public void saveFile(File file)
    {
                FileWriter fileWriter = null;
                PrintWriter printWriter = null;
                BufferedReader bufferedReader=null;
          try
            {
                //Opening a file in append mode using FileWriter
                fileWriter = new FileWriter(file,false);
                //Wrapping BufferedWriter object in PrintWriter
                printWriter = new PrintWriter(fileWriter);
                //Bringing cursor to next line
                //Writing text to file
                printWriter.print(NotePad.this.editor.getText());

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            finally
            {
                try
                {
                    printWriter.close();
                    fileWriter.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
