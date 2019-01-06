import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChangeLesson extends FrameItem implements ActionListener {

    private ResultSet lessons1;
    private Button[] lessonsButs1;
    private ResultSet lessons2;
    private Button[] lessonsButs2;
    private Button back;

    public ChangeLesson(Dimension dim) {
        super(dim, 5, 1);
        add(new Label("Уроки по словам"));
        addWords();
        add(new Label("Уроки по грамматике"));

        makeGrammar();
        back = new Button("Назад");
        back.addActionListener(this);
        add(back);
        setFont(font);
    }


    private void addWords(){
        Database db = new Database();
        lessons1 = db.select("SELECT * FROM lessons WHERE lang  = '"+Application.getInstance().lang+"'");
        ArrayList<String> langsList = new ArrayList<String>();
        try{
            while (lessons1.next()){
                langsList.add(makeTitle(lessons1));
            }
            lessons1.beforeFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        lessonsButs1 = new Button[langsList.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);
        add(buttons);

        for(int i = 0; i < lessonsButs1.length; i++){
            lessonsButs1[i] = new Button(langsList.get(i));
            lessonsButs1[i].addActionListener(this);
            lessonsButs1[i].setFont(font);
            buff.add(lessonsButs1[i]);
            buttons.revalidate();
        }
        add(buttons);
    }

    private void makeGrammar(){
        Database db = new Database();
        lessons2 = db.select("SELECT * FROM grammar_lessons WHERE lang = '"+Application.getInstance().lang+"'");
        ArrayList<String> langsList = new ArrayList<String>();
        try{
            while (lessons2.next()){
                langsList.add(makeTitle(lessons2));
            }
            lessons2.beforeFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        lessonsButs2 = new Button[langsList.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);

        for(int i = 0; i < lessonsButs2.length; i++){
            lessonsButs2[i] = new Button(langsList.get(i));
            lessonsButs2[i].addActionListener(this);
            lessonsButs2[i].setFont(font);
            buff.add(lessonsButs2[i]);
            buttons.revalidate();
        }
        add(buttons);
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
            while (lessons1.next()){
                if(actionEvent.getActionCommand().equals(makeTitle(lessons1))){
                    Database db = new Database();
                    ResultSet rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND category = '"+lessons1.getString("name")+"'  AND user='"+lessons1.getString("author")+"' ORDER BY RAND() LIMIT 15");
                    try{
                        while(rs.next()){
                            String[] wordData = new String[7];
                            wordData[0] = rs.getString("lang");
                            wordData[1] = rs.getString("word");
                            wordData[2] = rs.getString("translate");
                            wordData[3] = rs.getString("transcription");
                            wordData[4] = rs.getString("img");
                            wordData[5] = rs.getString("example");
                            wordData[6] = rs.getString("category");
                            Application.getInstance().words.add(wordData);
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

            Application.getInstance().frame.move(ProgramFrame.TEST_PAGE);
                }
            }
            while (lessons2.next()){
                if(actionEvent.getActionCommand().equals(makeTitle(lessons2))){
                    Application.getInstance().grammarCurrentId = lessons2.getInt("id");
                    Application.getInstance().grammarCurrentName = lessons2.getString("name");
                    Application.getInstance().grammarCurrentText = lessons2.getString("text");

                    Application.getInstance().frame.move(ProgramFrame.GRAMMAR_LOOK);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }

    }
}

