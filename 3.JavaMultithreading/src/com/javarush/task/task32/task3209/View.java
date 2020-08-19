package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.actions.RedoAction;
import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;
import com.javarush.task.task32.task3209.listeners.UndoMenuListener;
import javafx.event.Event;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private UndoManager undoManager = new UndoManager();



    private UndoListener undoListener = new UndoListener(undoManager);
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane  = new JEditorPane();

    public View()  {

        try {
            String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(systemLookAndFeelClassName);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public UndoListener getUndoListener() {
        return undoListener;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command  = actionEvent.getActionCommand();
        switch (command){
            case "Новый":{controller.createNewDocument(); break;}
            case "Открыть":{controller.openDocument();break;}
            case "Сохранить":{controller.saveDocument();break;}
            case "Сохранить как...":{controller.saveDocumentAs();break;}
            case "Выход":{controller.exit();break;}
            case "О программе":{this.showAbout();break;}
        }

    }
    public void selectedTabChanged(){
        try {
            if (tabbedPane.getSelectedIndex()==0){
                controller.setPlainText(plainTextPane.getText());
                resetUndo();
            }
            else if (tabbedPane.getSelectedIndex()==1){

                 plainTextPane.setText(controller.getPlainText());
                resetUndo();
            }

        } catch (Exception e) {
            ExceptionHandler.log(e);
        }


    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void init(){
        this.initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        this.setVisible(true);
    }
    public void exit(){this.controller.exit();}
    //Инициализация меню
    public void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        //jMenuBar.add()

        MenuHelper.initFileMenu(this,jMenuBar);
        MenuHelper.initEditMenu(this,jMenuBar);
        MenuHelper.initStyleMenu(this,jMenuBar);
        MenuHelper.initAlignMenu(this,jMenuBar);
        MenuHelper.initColorMenu(this,jMenuBar);
        MenuHelper.initFontMenu(this,jMenuBar);
        MenuHelper.initHelpMenu(this,jMenuBar);
        getContentPane().add(jMenuBar,BorderLayout.NORTH);


    }

    //Инициализация редактора
    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane = new JScrollPane(htmlTextPane);

        tabbedPane.add("HTML",jScrollPane);
        JScrollPane jScrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст",jScrollPane1);
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        tabbedPane.setPreferredSize(new Dimension(1000,1000));
        this.getContentPane().add(tabbedPane,BorderLayout.CENTER);

    }
    public void initGui(){
        this.initMenuBar();
        this.initEditor();
        this.pack();

    }

    public boolean canRedo(){
        return undoManager.canRedo();
    }
    public boolean canUndo(){
        return undoManager.canUndo();
    }
    public void undo(){
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }
    public void redo(){
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo()
    {
    undoManager.discardAllEdits();
    }
    public boolean isHtmlTabSelected(){

        if (tabbedPane.getSelectedIndex()==0)
        return true;
    else
        return false;
    }
    public void selectHtmlTab(){
        this.tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public void update()
    {
        Document document = this.controller.getDocument();
        htmlTextPane.setDocument(document);

    }
    public void showAbout(){
        JOptionPane.showMessageDialog(null,"This editor dewelope in user demonmr", "HTML Editor 1.0",JOptionPane.INFORMATION_MESSAGE);
    }
}
