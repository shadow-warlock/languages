package main.gui.pages;

import dto.objects.Category;
import dto.objects.Word;
import dto.objects.WordLesson;
import main.Application;
import main.api.CategoryAPI;
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

public class WordLessonEdit extends FrameItem implements ActionListener {

    private Button[] lessonsButs;
    private java.util.List<Word> langsList;
    private Button addWord;
    private Button remove;

    private TextField name;
    private Button add;
    private Button back;

    public WordLessonEdit(Dimension dim) {
        super(dim, 7, 1);
        name = new TextField();
        JPanel title = new JPanel(new GridLayout(3, 1));
        title.add(new Label("Создание\\редактирование урока по словам"));
        title.add(new Label("(При создании придумайте категорию и сохраните)"));
        title.add(new Label("(Затем отредактируйте полученный пустой урок, добавив туда слов)"));
        add(title);
        JPanel buttons = new JPanel(new GridLayout(3, 2));
        buttons.add(new Label("Категория"));
        buttons.add(name);
        buttons.add(new Label("Список слов:"));
        add(buttons);
        if(Application.getInstance().currentWordLesson != null){
            name.setText(Application.getInstance().currentWordLesson.getName());
            langsList = WordAPI.getWordsByLesson(Application.getInstance().lang.getLang(), Application.getInstance().currentWordLesson.getName());
            lessonsButs = new Button[langsList.size()];
            JPanel buff = new JPanel();
            buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
            JScrollPane wordsPane = new JScrollPane(buff);
            for(int i = 0; i < lessonsButs.length; i++){
                lessonsButs[i] = new Button(langsList.get(i).getWord());
                lessonsButs[i].addActionListener(this);
                lessonsButs[i].setFont(font);
                buff.add(lessonsButs[i]);
                wordsPane.revalidate();
            }
            add(wordsPane);
            addWord = new Button("Добавить слово");
            add(addWord);
            addWord.addActionListener(this);
        }

        back = new Button("Назад");
        back.addActionListener(this);
//        if(main.Application.getInstance().wordLessonCurrentName == null){
        remove = new Button("Удалить");
        add(remove);
        remove.addActionListener(this);
            add = new Button("Добавить\\изменить урок");
            add.addActionListener(this);
            add(add);
//        }

        add(back);
        buttons.setFont(font);
        title.setFont(font);
        back.setFont(font);
        setFont(font);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == add){
            if(Application.getInstance().currentWordLesson == null){
                Category newCat = new Category();
                newCat.setTitle(name.getText());
                newCat.setLang(Application.getInstance().lang.getLang());
                newCat.setUser(Application.getInstance().loggedUser.getLogin());
                CategoryAPI.replaceCategory(newCat);
                WordLesson newLesson = new WordLesson();
                newLesson.setName(name.getText());
                newLesson.setAuthor(Application.getInstance().loggedUser.getLogin());
                newLesson.setLang(Application.getInstance().lang.getLang());
                WordLessonAPI.replaceLesson(newLesson);
                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
            }else{
                WordLesson newLesson = new WordLesson();
                WordLessonAPI.deleteLesson(Application.getInstance().currentWordLesson);

                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
                newLesson.setName(name.getText());
                newLesson.setAuthor(Application.getInstance().loggedUser.getLogin());
                newLesson.setLang(Application.getInstance().lang.getLang());
                WordLessonAPI.replaceLesson(newLesson);

                Category newCat = new Category();
                newCat.setTitle(name.getText());
                newCat.setLang(Application.getInstance().lang.getLang());
                newCat.setUser(Application.getInstance().loggedUser.getLogin());
                CategoryAPI.replaceCategory(newCat);

                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
            }
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
            return;
        }
        for (int i = 0; i < lessonsButs.length; i++){
            if(actionEvent.getSource() == lessonsButs[i]){
                Application.getInstance().currentEditWord = langsList.get(i);
                Application.getInstance().frame.move(ProgramFrame.ADD_WORD_TO_LESSON);

            }
        }
        if(actionEvent.getSource() == addWord){
            Application.getInstance().frame.move(ProgramFrame.ADD_WORD_TO_LESSON);
        }
        if(actionEvent.getSource() == remove){
            WordLessonAPI.deleteLesson(Application.getInstance().currentWordLesson);
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().currentWordLesson = null;
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
        }

    }
}

