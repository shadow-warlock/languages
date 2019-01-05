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

    public ChangeAction(Dimension dim){
        super(dim, 3, 3);
        addWord = new Button("Добавить слово");
        addWord.addActionListener(this);
        category = new Button("Категории");
        category.addActionListener(this);
        lessons = new Button("Учиться");
        lessons.addActionListener(this);
        stats = new Button("Статистика");
        stats.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);
        add(new Label("Выберите действие. Язык выбран " + Application.getInstance().lang));
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        buttons.add(addWord);
        buttons.add(category);
        buttons.add(lessons);
        buttons.add(stats);

        add(buttons);
        add(back);

        setFont(font);
        buttons.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addWord){
            Application.getInstance().action = "add_word";
            Application.getInstance().frame.move(ProgramFrame.ADD_WORD);
        }
        if(actionEvent.getSource() == category){
            Application.getInstance().action = "add_category";
            Application.getInstance().frame.move(ProgramFrame.ADD_CATEGORY);

        }
        if(actionEvent.getSource() == lessons){
            Application.getInstance().action = "lessons";
            Application.getInstance().frame.move(ProgramFrame.CHANGE_CATEGORY);

        }
        if(actionEvent.getSource() == stats){
            Application.getInstance().action = "stats";
//            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_LANG);

        }
    }
}
