package main.gui.pages;

import dto.objects.GrammarLesson;
import dto.objects.Test;
import dto.objects.WordLesson;
import main.Application;
import main.api.GrammarLessonAPI;
import main.api.WordAPI;
import main.api.WordLessonAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static main.gui.pages.WordLessonsList.makeTitle;

public class ChangeLesson extends FrameItem implements ActionListener {

    private java.util.List<WordLesson> lessons1;
    private Button[] lessonsButs1;
    private java.util.List<GrammarLesson> lessons2;
    private Button[] lessonsButs2;
    private Button back;

    public ChangeLesson(Dimension dim) {
        super(dim, 5, 1);
        add(new Label("Уроки по словам"));
        addWords();
        add(new Label("Уроки по грамматике"));

        makeGrammar();
        back = new Button("Назад");
        back.addActionListener(this);
        add(back);
        setFont(font);
    }


    private void addWords(){
        lessons1 = WordLessonAPI.getAllLessonsByLang(Application.getInstance().lang.getLang());
        lessonsButs1 = new Button[lessons1.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);
        add(buttons);

        for(int i = 0; i < lessonsButs1.length; i++){
            lessonsButs1[i] = new Button(makeTitle(lessons1.get(i)));
            lessonsButs1[i].addActionListener(this);
            lessonsButs1[i].setFont(font);
            buff.add(lessonsButs1[i]);
            buttons.revalidate();
        }
        add(buttons);
    }

    private void makeGrammar(){
        lessons2 = GrammarLessonAPI.getAllLessonsByLang(Application.getInstance().lang.getLang());

        lessonsButs2 = new Button[lessons2.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);

        for(int i = 0; i < lessonsButs2.length; i++){
            lessonsButs2[i] = new Button(GrammarList.makeTitle(lessons2.get(i)));
            lessonsButs2[i].addActionListener(this);
            lessonsButs2[i].setFont(font);
            buff.add(lessonsButs2[i]);
            buttons.revalidate();
        }
        add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i=0; i< lessons1.size();i++){
            if(actionEvent.getActionCommand().equals(makeTitle(lessons1.get(i)))){
                Application.getInstance().currentWordLesson = lessons1.get(i);
                Application.getInstance().frame.move(ProgramFrame.TEST_PAGE);
                Test test = new Test();

                test.setWords(WordAPI.getWordsByCategory(Application.getInstance().lang.getLang(), Application.getInstance().loggedUser.getLogin(), lessons1.get(i).getName()));
                Application.getInstance().test = test;
            }
        }
        for (int i=0; i< lessons2.size();i++){
            if(actionEvent.getActionCommand().equals(GrammarList.makeTitle(lessons2.get(i)))){
                Application.getInstance().currentGrammarLesson = lessons2.get(i);
                Application.getInstance().frame.move(ProgramFrame.GRAMMAR_LOOK);
            }
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }

    }
}

