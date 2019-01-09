package main.gui.pages;

import dto.objects.GrammarLesson;
import main.Application;
import main.api.GrammarLessonAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrammarEdit extends FrameItem implements ActionListener {

    private TextField name;
    private TextArea text;
    private Button add;
    private Button remove;
    private Button back;

    public GrammarEdit(Dimension dim) {
        super(dim, 5, 1);
        add(new Label("Создание\\редактирование урока по грамматике"));
        name = new TextField();
        text = new TextArea();
        if(Application.getInstance().currentGrammarLesson != null){
            name.setText(Application.getInstance().currentGrammarLesson.getName());
            text.setText(Application.getInstance().currentGrammarLesson.getText());
        }
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        buttons.add(new Label("Название"));
        buttons.add(name);
        buttons.add(new Label("Текст"));
        buttons.add(text);
        back = new Button("Назад");
        back.addActionListener(this);
        add = new Button("Добавить\\изменить урок");
        add.addActionListener(this);
        remove = new Button("Удалить");
        remove.addActionListener(this);
        add(buttons);
        add(add);
        add(remove);
        add(back);
        buttons.setFont(font);
        back.setFont(font);
        setFont(font);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == add){
            GrammarLesson lesson = new GrammarLesson();
            lesson.setAuthor(Application.getInstance().loggedUser.getLogin());
            lesson.setId(Application.getInstance().currentGrammarLesson.getId());
            lesson.setLang(Application.getInstance().lang.getLang());
            lesson.setName(name.getText());
            lesson.setText(text.getText());
            GrammarLessonAPI.replaceLesson(lesson);
            Application.getInstance().currentGrammarLesson = null;
            Application.getInstance().frame.move(ProgramFrame.GRAMMAR);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().currentGrammarLesson = null;
            Application.getInstance().frame.move(ProgramFrame.GRAMMAR);
        }
        if(actionEvent.getSource() == remove){
            GrammarLesson lesson = new GrammarLesson();
            lesson.setId(Application.getInstance().currentGrammarLesson.getId());
            GrammarLessonAPI.deleteLesson(lesson);


            Application.getInstance().currentGrammarLesson = null;
            Application.getInstance().frame.move(ProgramFrame.GRAMMAR);
        }
    }
}

