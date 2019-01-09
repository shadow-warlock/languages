package main.gui.pages;

import main.Application;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeAction extends FrameItem implements ActionListener {


    private final Button addWord;
    private final Button category;
    private final Button lessons;
    private final Button stats;
    private final Button back;
    private final Button works;
    private final Button lessonsTest;

    public ChangeAction(Dimension dim){
        super(dim, 3, 3);
        addWord = new Button("Добавить слово");
        addWord.addActionListener(this);
        category = new Button("Категории");
        category.addActionListener(this);
        lessons = new Button("Тесты");
        lessons.addActionListener(this);
        lessonsTest = new Button("Уроки");
        lessonsTest.addActionListener(this);
        stats = new Button("Статистика");
        stats.addActionListener(this);
        works = new Button("Смотреть слова");
        works.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);
        add(new Label("Выберите действие. Язык выбран " + Application.getInstance().lang.getLang()));
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        buttons.add(addWord);
        buttons.add(category);
        buttons.add(lessons);
        buttons.add(lessonsTest);
        buttons.add(works);
        buttons.add(stats);

        add(buttons);
        add(back);

        setFont(font);
        buttons.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addWord){
            Application.getInstance().frame.move(ProgramFrame.ADD_WORD);
        }
        if(actionEvent.getSource() == category){
            Application.getInstance().frame.move(ProgramFrame.ADD_CATEGORY);

        }
        if(actionEvent.getSource() == lessons){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_CATEGORY);

        }
        if(actionEvent.getSource() == lessonsTest){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_LESSON);

        }
        if(actionEvent.getSource() == works){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_CATEGORY_WORDS);

        }
        if(actionEvent.getSource() == stats){
            Application.getInstance().frame.move(ProgramFrame.STATS);

        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_LANG);

        }
    }
}
