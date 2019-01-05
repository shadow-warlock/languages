import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ChangeCategory extends FrameItem implements ActionListener{
    private Button next;
    private Button back;
    private JComboBox category;

    private final String WITH_OUT_CATEGORY = "Без категории";
    private final String ALL_WORDS = "Любые слова";


    public ChangeCategory(Dimension dim){
        super(dim, 6, 1);
        next = new Button("Далее");
        next.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);

        Database db = new Database();
        ResultSet rs = db.select("SELECT * FROM categories WHERE lang = '" +Application.getInstance().lang+ "'");
        int size =0;
        String[] categories = new String[2];
        categories[0] = WITH_OUT_CATEGORY;
        categories[1] = ALL_WORDS;
        if (rs != null)
        {
            try {
                rs.beforeFirst();
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
                categories = new String[size+2];
                categories[0] = WITH_OUT_CATEGORY;
                categories[1] = ALL_WORDS;
                for(int i = 2; rs.next(); i++){
                    categories[i] = rs.getString("title");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        category = new JComboBox(categories);
        add(new Label("Выберите какие слова выдавать. Язык выбран " + Application.getInstance().lang));
        JPanel buttons = new JPanel(new GridLayout(1, 2));


        buttons.add(new Label("Категория"));
        buttons.add(category);
        add(buttons);
        add(next);
        add(back);

        setFont(font);
        buttons.setFont(font);
        category.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("ТЕСТ");
        if(actionEvent.getSource() == next){
            Application.getInstance().category = (String)category.getSelectedItem();
            Database db = new Database();
            ResultSet rs;
            if(((String) category.getSelectedItem()).equals(WITH_OUT_CATEGORY)){
                rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND category = '' ORDER BY RAND() LIMIT 15");

            }else if(((String) category.getSelectedItem()).equals(ALL_WORDS)){
                rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' ORDER BY RAND() LIMIT 15");
            }else{
                rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND category = '"+(String)category.getSelectedItem()+"' ORDER BY RAND() LIMIT 15");
            }
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
        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }

}
