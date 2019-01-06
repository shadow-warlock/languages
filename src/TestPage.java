import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestPage extends FrameItem implements ActionListener{

    private Button back;
    private Button answer1 = new Button();
    private Button answer2 = new Button();
    private Button answer3 = new Button();
    private Button answer4 = new Button();
    private JLabel label;
    private String[] word;

    public TestPage(Dimension dim){
        super(dim, 6, 1);
        back = new Button("Прервать тестирование");
        back.addActionListener(this);
        label = new JLabel();
        label.setFont(font);
        if(Application.getInstance().words.size() > Application.getInstance().wordNum) {
            word = Application.getInstance().words.get(Application.getInstance().wordNum);
            if(!word[4].equals("null")){
                try {
                    BufferedImage img = ImageIO.read(new File("imgs/" + word[4]));
                    label.setIcon(new ImageIcon(img));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                label.setText(word[1]);
            }
            textRandomize(new Button[]{answer1, answer2, answer3, answer4}, word);
        }
        else
            label.setText("Нет слов по вашему запросу");


        add(new Label("Язык " + Application.getInstance().lang + " что изображено на картинке (или написано, если картинки нет)"));
        add(label);

        JPanel buttons = new JPanel(new GridLayout(2, 2));

        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);

        add(buttons);
        add(back);

        setFont(font);
        buttons.setFont(font);
//        category.setFont(font);

    }

    private void textRandomize(Button buttons[], String[] word){

        int x = (int)(Math.random()*buttons.length);
        buttons[x].setLabel(word[2]);
        System.out.println(Arrays.toString(word));
        List<String[]> words = Application.getInstance().words;
        if(words.size() < buttons.length){
            for (int i=0; i< buttons.length; i++){
                buttons[i].setLabel("Слишком мало слов");
            }
        }else{
            for (int i=0; i< buttons.length; i++){
                buttons[i].addActionListener(this);
            }
            for (int i=0; i< buttons.length; i++){
                if(i == x)
                    continue;
                int y = (int)(Math.random()*buttons.length);
                while(words.get(y)[2].equals(word[2])){
                    y = (int)(Math.random()*buttons.length);
                }
                System.out.println(words.size());
                System.out.println(y);
                buttons[i].setLabel(words.get(y)[2]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == back){
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }else{
            if(word != null && actionEvent.getActionCommand().equals(word[2])){
                Application.getInstance().trues++;
                Database db = new Database();
                db.insert("INSERT INTO stats VALUES(null, '"+word[2]+"', '" +Application.getInstance().lang+ "', '"+Application.getInstance().user+"', 1)");
                if(Application.getInstance().wordNum < Application.getInstance().words.size()-1){
                    Application.getInstance().wordNum++;
                    Application.getInstance().frame.move(ProgramFrame.TEST_PAGE);
                }else{
                    label.setIcon(null);
                    label.setText("Тест окончен. Результат: ошибок - " + Application.getInstance().errors + "; Правильно - " + Application.getInstance().trues);
                    Application.getInstance().trues = 0;
                    Application.getInstance().errors = 0;
                    Application.getInstance().wordNum = 0;
                    answer1.removeActionListener(this);
                    answer2.removeActionListener(this);
                    answer3.removeActionListener(this);
                    answer4.removeActionListener(this);

                }

            }else{
                Application.getInstance().errors++;
                Database db = new Database();
                db.insert("INSERT INTO stats VALUES(null, '"+word[2]+"', '" +Application.getInstance().lang+ "', '"+Application.getInstance().user+"', 0)");
                ((Button)actionEvent.getSource()).setBackground(Color.red);
            }
        }
    }

}
