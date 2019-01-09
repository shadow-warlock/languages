package main.gui.pages;

import dto.objects.Category;
import main.Application;
import main.api.CategoryAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCategory extends FrameItem implements ActionListener{

    private final Button addCategory;
    private final Button back;
    private TextField category;



    public AddCategory(Dimension dim){
        super(dim, 4, 1);
        add(new Label("Добавьте категорию. Язык выбран " + Application.getInstance().lang.getLang()));
        addCategory = new Button("Добавить категорию");
        addCategory.addActionListener(this);
        java.util.List<Category> categories = CategoryAPI.getCategoriesByLangAndUser(Application.getInstance().lang.getLang(), Application.getInstance().loggedUser.getLogin());
        String[] titles = new String[categories.size()];
        for(int i = 0; i < titles.length; i++){
            titles[i] = categories.get(i).getTitle();
        }
        JList list = new JList(titles);
        list.setFont(font);
        JScrollPane sp = new JScrollPane(list);
        add(sp);
        category = new TextField();
        back = new Button("Назад");
        back.addActionListener(this);
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(new Label("Категория"));
        buttons.add(category);
        buttons.add(addCategory);

        add(buttons);

        add(back);
        setFont(font);
        buttons.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addCategory){
            Category newCat = new Category();
            newCat.setUser(Application.getInstance().loggedUser.getLogin());
            newCat.setLang(Application.getInstance().lang.getLang());
            newCat.setTitle(category.getText());
            CategoryAPI.replaceCategory(newCat);
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }

}
