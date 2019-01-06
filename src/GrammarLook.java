import javax.swing.*;
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
        if(Application.getInstance().grammarCurrentId != null){
            name.setText(Application.getInstance().grammarCurrentName);
            text.setText(Application.getInstance().grammarCurrentText);
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
            Application.getInstance().grammarCurrentId = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }

    }
}

