import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GrammarEdit extends FrameItem implements ActionListener {

    private TextField name;
    private TextArea text;
    private Button add;
    private Button back;

    public GrammarEdit(Dimension dim) {
        super(dim, 4, 1);
        add(new Label("Создание\\редактирование урока по грамматике"));
        name = new TextField();
        text = new TextArea();
        if(Application.getInstance().grammarCurrentId != null){
            name.setText(Application.getInstance().grammarCurrentName);
            text.setText(Application.getInstance().grammarCurrentText);
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
        add(buttons);
        add(add);
        add(back);
        buttons.setFont(font);
        back.setFont(font);
        setFont(font);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == add){
            Database db = new Database();
            db.insert("INSERT INTO grammar_lessons VALUES("+Application.getInstance().grammarCurrentId+", '"+name.getText()+"', '"+text.getText()+"', '"+Application.getInstance().user+"', '"+Application.getInstance().lang+"') on duplicate key update name = '"+name.getText()+"', text='"+text.getText()+"'");
            Application.getInstance().grammarCurrentId = null;
            Application.getInstance().frame.move(ProgramFrame.GRAMMAR);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().grammarCurrentId = null;
            Application.getInstance().frame.move(ProgramFrame.GRAMMAR);
        }

    }
}

