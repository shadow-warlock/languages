import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordLessonsList extends FrameItem implements ActionListener {

    private ResultSet lessons;
    private Button[] lessonsButs;
    private Button add;
    private Button back;

    public WordLessonsList(Dimension dim) {
        super(dim, 4, 1);
        add(new Label("Уроки по словам"));
        Database db = new Database();
        lessons = db.select("SELECT * FROM lessons WHERE lang  = '"+Application.getInstance().lang+"'");
        ArrayList<String> langsList = new ArrayList<String>();
        try{
            while (lessons.next()){
                langsList.add(makeTitle(lessons));
            }
            lessons.beforeFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        back = new Button("Назад");
        back.addActionListener(this);
        lessonsButs = new Button[langsList.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);
        add(buttons);

        for(int i = 0; i < lessonsButs.length; i++){
            lessonsButs[i] = new Button(langsList.get(i));
            lessonsButs[i].addActionListener(this);
            lessonsButs[i].setFont(font);
            buff.add(lessonsButs[i]);
            buttons.revalidate();
        }
        add(buttons);
        add = new Button("Добавить урок");
        add.addActionListener(this);
        add(add);
        add(back);
        setFont(font);
    }

    private String makeTitle(ResultSet lessons){
        try {
            return lessons.getString("name") + "; Автор " + lessons.getString("author");
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            while (lessons.next()){
                if(actionEvent.getActionCommand().equals(makeTitle(lessons))){
                    Application.getInstance().wordLessonCurrentName = lessons.getString("name");
                    Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        if(actionEvent.getSource() == add){
            Application.getInstance().wordLessonCurrentName = null;
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
        }
    }
}

