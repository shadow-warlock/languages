import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Registration extends FrameItem implements ActionListener {
    private Button reg;


    private TextField login;
    private JPasswordField password;
    private JLabel text = new JLabel("Придумайте логин (не больше 25 символов) и пароль для входа в программу.");


    public Registration(Dimension dim){
        super(dim, 4, 1);
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
        add(reg);

        setFont(font);
        buttons.setFont(font);
        buttons2.setFont(font);
        text.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == reg){
            Database db = new Database();
            db.insert("INSERT INTO users VALUES('"+login.getText()+"', '"+password.getText()+"', '"+LogIn.SCHOLAR+"')");
            Application.getInstance().frame.move(ProgramFrame.LOG_IN);
        }
    }
}
