package main.gui.pages;

import dto.objects.Language;
import main.Application;
import main.api.LanguageAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends FrameItem implements ActionListener {
    private Button[] langs;
    private Button add;
    private TextField newLang;
    private Button back;


    public Menu(Dimension dim){
        super(dim, 4, 1);
        java.util.List<Language> langsList = LanguageAPI.getLanguages(Application.getInstance().loggedUser.getLogin());
        back = new Button("Назад");
        back.addActionListener(this);
        add = new Button("Добавить язык");
        add.addActionListener(this);
        newLang = new TextField();
        add(new Label("Выбери язык, " + Application.getInstance().loggedUser.getLogin()));
        langs = new Button[langsList.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);
        add(buttons);

        for(int i = 0; i < langs.length; i++){
            langs[i] = new Button(langsList.get(i).getLang());
            langs[i].addActionListener(this);
            langs[i].setFont(font);
            buff.add(langs[i]);
            buttons.revalidate();

        }
        JPanel buttonsAdd = new JPanel(new GridLayout(1, 2));
        buttonsAdd.add(newLang);
        buttonsAdd.add(add);
        add(buttonsAdd);
        add(back);
        setFont(font);
        buttonsAdd.setFont(font);
        buttons.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == add){
            Language lang = new Language();
            lang.setUser(Application.getInstance().loggedUser.getLogin());
            lang.setLang(newLang.getText());
            LanguageAPI.replaceLanguage(lang);
            Application.getInstance().frame.move(ProgramFrame.CHANGE_LANG);
        }

        for(int i = 0; i < langs.length; i++){
            if(actionEvent.getSource() == langs[i]){
                Language lang = new Language();
                lang.setUser(Application.getInstance().loggedUser.getLogin());
                lang.setLang(langs[i].getLabel());
                Application.getInstance().lang = lang;
                Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
            }
        }

        if(actionEvent.getSource() == back){
            Application.getInstance().loggedUser = null;
            Application.getInstance().frame.move(ProgramFrame.LOG_IN);
        }
    }
}
