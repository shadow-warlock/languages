import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stats extends FrameItem implements ActionListener{
    private Button back;


    public Stats(Dimension dim){
        super(dim, 6, 1);

        back = new Button("Назад");
        back.addActionListener(this);
        Database db = new Database();
        try {
            ResultSet countWords = db.select("SELECT COUNT(*) FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND user='"+Application.getInstance().user+"'");
            countWords.next();
            add(new Label("Всего слов добавлено " + countWords.getInt("COUNT(*)")));
            countWords.close();
            ResultSet countTests = db.select("SELECT COUNT(*) FROM stats WHERE lang = '" +Application.getInstance().lang+ "' AND user='"+Application.getInstance().user+"'");
            countTests.next();
            add(new Label("Всего дано ответов в тестах " + countTests.getInt("COUNT(*)")));
            countTests.close();
            ResultSet percentTrue = db.select("SELECT COUNT(IF(result=1, 1, null))/COUNT(*)*100 FROM stats WHERE lang = '" +Application.getInstance().lang+ "' AND user='"+Application.getInstance().user+"'");
            percentTrue.next();
            add(new Label("Процент ошибок " + (percentTrue.getInt("COUNT(IF(result=1, 1, null))/COUNT(*)*100")) + "%"));
            ResultSet popularFalse = db.select("SELECT word FROM stats WHERE lang = '" +Application.getInstance().lang+ "' AND user='"+Application.getInstance().user+"' GROUP BY word ORDER BY COUNT(IF(result=0, 1, null)) LIMIT 3");
            String pop = "";
            while (popularFalse.next()){
                pop += popularFalse.getString("word") + ", ";
            }
            pop = pop.substring(0, pop.length()-2);
            add(new Label("Самые популярные ошибки :" + pop));
            percentTrue.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        add(back);
        setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }

}
