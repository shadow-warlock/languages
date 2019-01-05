import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogIn extends FrameItem implements ActionListener {
    private Button logIn;
    private Button reg;


    public static final String TEACHER = "Учитель";
    public static final String SCHOLAR = "Ученик";


    private TextField login;
    private JPasswordField password;
    private JLabel text = new JLabel("Введите логин и пароль для входа в программу.");


    public LogIn(Dimension dim){
        super(dim, 5, 1);
        logIn = new Button("Войти");
        logIn.addActionListener(this);
        reg = new Button("Зарегистрироваться");
        reg.addActionListener(this);
        login = new TextField();
        password = new JPasswordField();

        JPanel buttons = new JPanel(new GridLayout(2, 2));
        JPanel buttons2 = new JPanel(new GridLayout(2, 2));
        add(text);
        buttons.add(new Label("Логин"));
        buttons.add(login);
        buttons2.add(new Label("Пароль"));
        buttons2.add(password);
        add(buttons);
        add(buttons2);
        add(logIn);
        add(reg);

        setFont(font);
        buttons.setFont(font);
        buttons2.setFont(font);
        text.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == logIn){
            Database db = new Database();
            ResultSet users = db.select("SELECT * FROM users WHERE login = '"+login.getText()+"' AND password = '"+password.getText()+"'  ");
            try{
                while (users.next()) {
                    String type = users.getString("type");
                    if(type.equals(TEACHER)){
                        Application.getInstance().userType = UserType.TEACHER;
                    }
                    if(type.equals(SCHOLAR)){
                        Application.getInstance().userType = UserType.SCHOLAR;
                    }
                    Application.getInstance().user = users.getString("login");
                    Application.getInstance().frame.move(ProgramFrame.CHANGE_LANG);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        if(actionEvent.getSource() == reg){
            Application.getInstance().frame.move(ProgramFrame.REGISTRATION);
        }
    }
}
