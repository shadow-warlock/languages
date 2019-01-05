import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordLessonEdit extends FrameItem implements ActionListener {

    private Button[] lessonsButs;
    private Button addWord;

    private TextField name;
    private Button add;
    private Button back;

    public WordLessonEdit(Dimension dim) {
        super(dim, 5, 1);
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
        if(Application.getInstance().wordLessonCurrentName != null){
            name.setText(Application.getInstance().wordLessonCurrentName);
            Database db = new Database();
            ResultSet lessons = db.select("SELECT * FROM words WHERE word IN (SELECT word FROM lessons_words WHERE lesson IN (SELECT name FROM lessons WHERE name = '"+Application.getInstance().wordLessonCurrentName+"' AND lang = '"+Application.getInstance().lang+"')) ");
            ArrayList<String> langsList = new ArrayList<String>();
            try{
                while (lessons.next()){
                    langsList.add(lessons.getString("word"));
                }
                lessons.beforeFirst();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            lessonsButs = new Button[langsList.size()];
            JPanel buff = new JPanel();
            buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
            JScrollPane wordsPane = new JScrollPane(buff);
            for(int i = 0; i < lessonsButs.length; i++){
                lessonsButs[i] = new Button(langsList.get(i));
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
        if(Application.getInstance().wordLessonCurrentName == null){
            add = new Button("Добавить\\изменить урок");
            add.addActionListener(this);
            add(add);
        }

        add(back);
        buttons.setFont(font);
        title.setFont(font);
        back.setFont(font);
        setFont(font);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == add){
            if(Application.getInstance().wordLessonCurrentName == null){
                Database db = new Database();
                db.insert("INSERT INTO categories VALUES('"+Application.getInstance().user+"', '"+name.getText()+"', '"+Application.getInstance().lang+"')");
                db.insert("INSERT INTO lessons VALUES('"+name.getText()+"', '"+Application.getInstance().user+"', '"+Application.getInstance().lang+"')");
                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
            }

        }
        if(actionEvent.getSource() == addWord){
            Application.getInstance().frame.move(ProgramFrame.ADD_WORD_TO_LESSON);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().wordLessonCurrentName = null;
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON);
        }

    }
}

