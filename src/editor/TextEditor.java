package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TextEditor extends JFrame {
    File currentFile;
    private final JTextArea textArea;

    public TextEditor() {
        setTitle("Simple Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        setJMenuBar(createMenuBar());

        add(scrollPane, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem findReplace = new JMenuItem("Find & Replace");


        editMenu.addSeparator();
        editMenu.add(findReplace);

        newFile.addActionListener(_ -> FileManager.newFile(this, textArea));
        open.addActionListener(_ -> FileManager.openFile(this, textArea));
        save.addActionListener(_ -> FileManager.saveFile(this, textArea));

        cut.addActionListener(_ -> ActionHandlers.cut(textArea));
        copy.addActionListener(_ -> ActionHandlers.copy(textArea));
        paste.addActionListener(_ -> ActionHandlers.paste(textArea));
        findReplace.addActionListener(_ -> {
            FindReplaceDialog dialog = new FindReplaceDialog(this, textArea);
            dialog.setVisible(true);
        });


        fileMenu.add(newFile);
        fileMenu.addSeparator();
        fileMenu.add(open);
        fileMenu.add(save);

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        return menuBar;
    }
}
