package main.gui.pages;

import main.Application;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordsLook extends FrameItem implements ActionListener {

    private Button[] lessonsButs;
    private TextField name;
    private Button back;

    public WordsLook(Dimension dim) {
        super(dim, 3, 1);
        name = new TextField();
        add(new Label("Список слов"));
        name.setText(Application.getInstance().currentWordLesson.getName());
        lessonsButs = new Button[Application.getInstance().looksWords.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane wordsPane = new JScrollPane(buff);
        for(int i = 0; i < lessonsButs.length; i++){
            lessonsButs[i] = new Button(Application.getInstance().looksWords.get(i).getWord());
            lessonsButs[i].addActionListener(this);
            lessonsButs[i].setFont(font);
            buff.add(lessonsButs[i]);
            wordsPane.revalidate();
        }
        add(wordsPane);

        back = new Button("Назад");
        back.addActionListener(this);

        add(back);
        back.setFont(font);
        setFont(font);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < lessonsButs.length; i++){
            if(actionEvent.getSource() == lessonsButs[i]){
                Application.getInstance().currentEditWord = Application.getInstance().looksWords.get(i);
                Application.getInstance().frame.move(ProgramFrame.ADD_WORD);

            }
        }

        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        Application.getInstance().looksWords = null;
    }
}

