package main.gui.pages;

import dto.objects.Category;
import dto.objects.Test;
import main.Application;
import main.api.CategoryAPI;
import main.api.WordAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeCategoryWords extends FrameItem implements ActionListener{
    private Button next;
    private Button back;
    private JComboBox category;

    private final String WITH_OUT_CATEGORY = "Без категории";
    private final String ALL_WORDS = "Любые слова";


    public ChangeCategoryWords(Dimension dim){
        super(dim, 6, 1);
        next = new Button("Далее");
        next.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);

        java.util.List<Category> list = CategoryAPI.getCategoriesByLangAndUser(Application.getInstance().lang.getLang(), Application.getInstance().loggedUser.getLogin());
        String[] categories = new String[list.size()+2];
        categories[0] = WITH_OUT_CATEGORY;
        categories[1] = ALL_WORDS;
        for(int i = 0; i< list.size(); i++){
            categories[i+2] = list.get(i).getTitle();
        }
        category = new JComboBox(categories);
        add(new Label("Какие слова смотреть. Язык выбран " + Application.getInstance().lang));
        JPanel buttons = new JPanel(new GridLayout(1, 2));


        buttons.add(new Label("Категория"));
        buttons.add(category);
        add(buttons);
        add(next);
        add(back);

        setFont(font);
        buttons.setFont(font);
        category.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == next){
            String cat = "";
            if(((String) category.getSelectedItem()).equals(WITH_OUT_CATEGORY)){
                cat = "";
            }else if(((String) category.getSelectedItem()).equals(ALL_WORDS)){
                cat = null;
            }else{
                cat = (String) category.getSelectedItem();
            }
            if(cat != null){
                Application.getInstance().looksWords = (WordAPI.getWordsByCategory(Application.getInstance().lang.getLang(), Application.getInstance().loggedUser.getLogin(), cat));
            }else{
                Application.getInstance().looksWords = (WordAPI.getWords(Application.getInstance().lang.getLang(), Application.getInstance().loggedUser.getLogin()));

            }

            Application.getInstance().frame.move(ProgramFrame.WORDS_LOOK);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }

}
