import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramFrame extends JFrame {


    private FrameItem currentItem = null;
    private Dimension dim;

    public static final String LOG_IN = "log_in";
    public static final String REGISTRATION = "registration";

    public static final String CHANGE_LANG = "change_lang";
    public static final String CHANGE_ACTION = "change_action";
    public static final String CHANGE_CATEGORY = "change_category";
    public static final String ADD_WORD = "add_word";
    public static final String ADD_CATEGORY = "add_category";
    public static final String TEST_PAGE = "test_page";


    public ProgramFrame(){
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)dim.getWidth() - insets.left - insets.right, (int)dim.getHeight() - insets.top - insets.bottom);
        setTitle("Изучение языков");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentItem = new LogIn(dim);
        setContentPane(currentItem);

        setVisible(true);
    }

    public void move(String action){
        switch (action){
            case REGISTRATION:
                remove(currentItem);
                currentItem = new Registration(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case LOG_IN:
                remove(currentItem);
                currentItem = new LogIn(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case CHANGE_LANG:
                remove(currentItem);
                currentItem = new Menu(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case CHANGE_ACTION:
                remove(currentItem);
                if(Application.getInstance().userType == UserType.TEACHER)
                    currentItem = new ChangeActionTeather(dim);
                else
                    currentItem = new ChangeAction(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case CHANGE_CATEGORY:
                remove(currentItem);
                currentItem = new ChangeCategory(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case ADD_WORD:
                remove(currentItem);
                currentItem = new AddWord(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case ADD_CATEGORY:
                remove(currentItem);
                currentItem = new AddCategory(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case TEST_PAGE:
                remove(currentItem);
                currentItem = new TestPage(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
        }
    }


}
