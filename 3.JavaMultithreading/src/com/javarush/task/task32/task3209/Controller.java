package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }
    public void init(){}
    public void exit(){ System.exit(0);}

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void setDocument(HTMLDocument document) {
        this.document = document;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }
    public void resetDocument(){
        UndoListener listener = view.getUndoListener();
        if (this.document!=null)
        this.document.removeUndoableEditListener(listener);
        this.document = (HTMLDocument)new HTMLEditorKit().createDefaultDocument();
        this.document.addUndoableEditListener(listener);
        this.view.update();
    }
    public void setPlainText(String text){

        try {
            resetDocument();
            StringReader stringReader = new StringReader(text);
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.read(stringReader,this.document,0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public String getPlainText(){
        try {
            StringWriter stringWriter = new StringWriter();
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.write(stringWriter,this.document,0,this.document.getLength());
            return stringWriter.toString();
        } catch (Exception e) {
            ExceptionHandler.log(e);
            return null;
        }
    }
    public void createNewDocument(){}
    public void openDocument(){}
    public void saveDocument(){}
    public void saveDocumentAs(){}
    public void showAbout(){}


    public static void main(String[] args) {

        View view = new View();

        Controller controller = new Controller(view);

        view.setController(controller);
        view.init();
        controller.init();


    }
}
