import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends FrameItem implements ActionListener {
    private Button[] langs;
    private Button back;


    public Menu(Dimension dim){
        super(dim, 3, 3);
        Database db = new Database();
        ResultSet dbLangs = db.select("SELECT * FROM langs WHERE user = '"+Application.getInstance().user+"'");
        back = new Button("Назад");
        back.addActionListener(this);
        add(new Label("Выбери язык, " + Application.getInstance().user));
        JPanel buttons = new JPanel(new GridLayout(3, 1));
        ArrayList<String> langs = new ArrayList<String>();
        try{
            while (dbLangs.next()){
                langs.add(dbLangs.getString("lang"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        add(buttons);
        add(back);
        setFont(font);
        buttons.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        if(actionEvent.getSource() == changeEn){
//            Application.getInstance().lang = "Английский";
//            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
//        }
//        if(actionEvent.getSource() == changeJp){
//            Application.getInstance().lang = "Японский";
//            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
//
//        }
//        if(actionEvent.getSource() == changeHr){
//            Application.getInstance().lang = "Немецкий";
//            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
//        }
        if(actionEvent.getSource() == back){
            Application.getInstance().userType = null;
            Application.getInstance().user = null;
            Application.getInstance().frame.move(ProgramFrame.LOG_IN);
        }
    }
}
