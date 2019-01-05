import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCategory extends FrameItem implements ActionListener{

    private final Button addCategory;
    private final Button back;
    private TextField category;



    public AddCategory(Dimension dim){
        super(dim, 4, 1);
        add(new Label("Добавьте категорию. Язык выбран " + Application.getInstance().lang));
        addCategory = new Button("Добавить категорию");
        addCategory.addActionListener(this);
        Database db = new Database();
        ResultSet rs = db.select("SELECT * FROM categories WHERE lang = \"" +Application.getInstance().lang+"\"");
        String[] categoriesArray = new String[0];
        int size = 0;
        if (rs != null)
        {
            try {
                rs.beforeFirst();
                rs.last();
                size = rs.getRow();
                rs.beforeFirst();
                categoriesArray = new String[size];
                for(int i = 0; rs.next(); i++){
                    categoriesArray[i] = rs.getString("title");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        JList list = new JList(categoriesArray);
        list.setFont(font);
        JScrollPane sp = new JScrollPane(list);
        add(sp);
        category = new TextField();
        back = new Button("Назад");
        back.addActionListener(this);
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(new Label("Категория"));
        buttons.add(category);
        buttons.add(addCategory);

        add(buttons);

        add(back);
        setFont(font);
        buttons.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addCategory){
            Database db = new Database();
            db.insert("INSERT INTO categories VALUES('"+category.getText()+"', '"+Application.getInstance().lang+"')");

            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }

}
