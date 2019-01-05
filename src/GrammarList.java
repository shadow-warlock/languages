import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GrammarList extends FrameItem implements ActionListener {

    private ResultSet lessons;
    private Button[] lessonsButs;

    public GrammarList(Dimension dim) {
        super(dim, 3, 3);
        Database db = new Database();
        lessons = db.select("SELECT * FROM grammar_lessons");
        ArrayList<String> langsList = new ArrayList<String>();
        try{
            while (lessons.next()){
                langsList.add(makeTitle(lessons));
            }
            lessons.beforeFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    }

    private String makeTitle(ResultSet lessons){
        try {
            return  "Запись №" +lessons.getString("id") + "; " + lessons.getString("name") + "; Автор " + lessons.getString("author");
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}

