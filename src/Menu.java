import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends FrameItem implements ActionListener {
    private Button[] langs;
    private Button add;
    private TextField newLang;
    private Button back;


    public Menu(Dimension dim){
        super(dim, 4, 1);
        Database db = new Database();
        ResultSet dbLangs = db.select("SELECT * FROM langs WHERE user = '"+Application.getInstance().user+"'");
        back = new Button("Назад");
        back.addActionListener(this);
        add = new Button("Добавить язык");
        add.addActionListener(this);
        newLang = new TextField();
        add(new Label("Выбери язык, " + Application.getInstance().user));
        ArrayList<String> langsList = new ArrayList<String>();
        try{
            while (dbLangs.next()){
                langsList.add(dbLangs.getString("lang"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        langs = new Button[langsList.size()];
        JPanel buff = new JPanel();
        buff.setLayout(new BoxLayout(buff, BoxLayout.Y_AXIS));
        JScrollPane buttons = new JScrollPane(buff);
        add(buttons);

        for(int i = 0; i < langs.length; i++){
            langs[i] = new Button(langsList.get(i));
            langs[i].addActionListener(this);
            langs[i].setFont(font);
            buff.add(langs[i]);
            buttons.revalidate();

        }
        JPanel buttonsAdd = new JPanel(new GridLayout(1, 2));
        buttonsAdd.add(newLang);
        buttonsAdd.add(add);
        add(buttonsAdd);
        add(back);
        setFont(font);
        buttonsAdd.setFont(font);
        buttons.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == add){
            Database db = new Database();
            db.insert("INSERT INTO langs VALUES('"+newLang.getText()+"', '"+Application.getInstance().user+"')");
            Application.getInstance().frame.move(ProgramFrame.CHANGE_LANG);
        }

        for(int i = 0; i < langs.length; i++){
            if(actionEvent.getSource() == langs[i]){
                Application.getInstance().lang = langs[i].getLabel();
                Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
            }
        }

        if(actionEvent.getSource() == back){
            Application.getInstance().userType = null;
            Application.getInstance().user = null;
            Application.getInstance().frame.move(ProgramFrame.LOG_IN);
        }
    }
}
