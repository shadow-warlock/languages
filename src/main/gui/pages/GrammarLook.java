package main.gui.pages;

import dto.objects.GrammarLesson;
import main.Application;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrammarLook extends FrameItem implements ActionListener {

    private TextField name;
    private TextArea text;
    private Button back;

    public GrammarLook(Dimension dim) {
        super(dim, 4, 1);
        add(new Label("Урок по грамматике"));
        name = new TextField();
        name.setEditable(false);

        text = new TextArea();
        text.setEditable(false);
        GrammarLesson les = Application.getInstance().currentGrammarLesson;
        if(les != null){
            name.setText(les.getName());
            text.setText(les.getText());
        }
        add(name);
        add(text);
        back = new Button("Назад");
        back.addActionListener(this);
        add(back);
        back.setFont(font);
        setFont(font);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == back){
            Application.getInstance().currentGrammarLesson = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }

    }
}

