import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeActionTeather extends FrameItem implements ActionListener {


    private final Button grammarLesson;
    private final Button wordsLesson;
    private final Button back;

    public ChangeActionTeather(Dimension dim){
        super(dim, 3, 3);
        grammarLesson = new Button("Уроки по грамматике");
        grammarLesson.addActionListener(this);
        wordsLesson = new Button("Уроки по словам");
        wordsLesson.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);
        add(new Label("Выберите действие. Язык выбран " + Application.getInstance().lang));
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        buttons.add(grammarLesson);
        buttons.add(wordsLesson);
        add(buttons);
        add(back);

        setFont(font);
        buttons.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == grammarLesson){
            Application.getInstance().action = "add_word";
            Application.getInstance().frame.move(ProgramFrame.ADD_WORD);
        }
        if(actionEvent.getSource() == wordsLesson){
            Application.getInstance().action = "add_category";
            Application.getInstance().frame.move(ProgramFrame.ADD_CATEGORY);

        }
        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_LANG);

        }
    }
}
