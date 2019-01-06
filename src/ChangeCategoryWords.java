import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeCategoryWords extends FrameItem implements ActionListener{
    private Button next;
    private Button back;
    private JComboBox category;

    private final String WITH_OUT_CATEGORY = "Без категории";
    private final String ALL_WORDS = "Любые слова";


    public ChangeCategoryWords(Dimension dim){
        super(dim, 6, 1);
        next = new Button("Далее");
        next.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);

        Database db = new Database();
        ResultSet rs = db.select("SELECT * FROM categories WHERE lang = '" +Application.getInstance().lang+ "' AND user='"+Application.getInstance().user+"'");
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
        add(new Label("Какие слова смотреть. Язык выбран " + Application.getInstance().lang));
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
                rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND category IS NULL AND user='"+Application.getInstance().user+"'");
            }else if(((String) category.getSelectedItem()).equals(ALL_WORDS)){
                rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND user='"+Application.getInstance().user+"'");
            }else{
                rs = db.select("SELECT * FROM words WHERE lang = '" +Application.getInstance().lang+ "' AND category = '"+(String)category.getSelectedItem()+"'  AND user='"+Application.getInstance().user+"'");
            }
            try{
                while(rs.next()){
                    Application.getInstance().looksWords.add(rs.getString("word"));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

            Application.getInstance().frame.move(ProgramFrame.WORDS_LOOK);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }

}
