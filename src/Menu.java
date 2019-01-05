import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends FrameItem implements ActionListener {
    private Button changeEn;
    private Button changeJp;
    private Button changeHr;


    public Menu(Dimension dim){
        super(dim, 3, 3);
        changeEn = new Button("Английский");
        changeEn.addActionListener(this);
        changeJp = new Button("Японский");
        changeJp.addActionListener(this);
        changeHr = new Button("Немецкий");
        changeHr.addActionListener(this);
        add(new Label("Выбери язык, " + Application.getInstance().user));
        JPanel buttons = new JPanel(new GridLayout(3, 1));
        buttons.add(changeEn);
        buttons.add(changeHr);
        buttons.add(changeJp);
        add(buttons);
        setFont(font);
        buttons.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == changeEn){
            Application.getInstance().lang = "Английский";
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        if(actionEvent.getSource() == changeJp){
            Application.getInstance().lang = "Японский";
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
        if(actionEvent.getSource() == changeHr){
            Application.getInstance().lang = "Немецкий";
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }
}
