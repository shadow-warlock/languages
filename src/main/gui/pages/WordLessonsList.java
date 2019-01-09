package main.gui.pages;

import dto.objects.WordLesson;
import main.Application;
import main.api.WordLessonAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordLessonsList extends FrameItem implements ActionListener {

    private java.util.List<WordLesson> lessons;
    private Button[] lessonsButs;
    private Button add;
    private Button back;

    public WordLessonsList(Dimension dim) {
        super(dim, 4, 1);
        add(new Label("Уроки по словам"));

        lessons = WordLessonAPI.getAllLessonsByLang(Application.getInstance().lang.getLang());
        back = new Button("Назад");
        back.addActionListener(this);
        lessonsButs = new Button[lessons.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);
        add(buttons);

        for(int i = 0; i < lessonsButs.length; i++){
            lessonsButs[i] = new Button(makeTitle(lessons.get(i)));
            lessonsButs[i].addActionListener(this);
            lessonsButs[i].setFont(font);
            buff.add(lessonsButs[i]);
            buttons.revalidate();
        }
        add(buttons);
        add = new Button("Добавить урок");
        add.addActionListener(this);
        add(add);
        add(back);
        setFont(font);
    }

    public static String makeTitle(WordLesson lesson){
        return lesson.getName() + "; Автор " + lesson.getAuthor();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i=0; i< lessons.size();i++){
            if(actionEvent.getActionCommand().equals(makeTitle(lessons.get(i)))){
                Application.getInstance().currentWordLesson = lessons.get(i);
                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
            }
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        if(actionEvent.getSource() == add){
            Application.getInstance().currentWordLesson = null;
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
        }
    }
}

