package main.gui.pages;

import dto.objects.Stat;
import dto.objects.Test;
import dto.objects.Word;
import main.Application;
import main.api.StatAPI;
import main.gui.FrameItem;
import main.gui.ProgramFrame;

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
    private Word word;

    public TestPage(Dimension dim){
        super(dim, 6, 1);
        back = new Button("Прервать тестирование");
        back.addActionListener(this);
        label = new JLabel();
        label.setFont(font);
        Test test = Application.getInstance().test;
        System.out.println(test);
        System.out.println(test.getWords());
        if(test.getWords().size() > test.getNum()) {
            word = test.getWords().get(test.getNum());
            if(!word.getImg().equals("null")){
                try {
                    BufferedImage img = ImageIO.read(new File("imgs/" + word.getImg()));
                    label.setIcon(new ImageIcon(img));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                label.setText(word.getTranslate());
            }
            textRandomize(new Button[]{answer1, answer2, answer3, answer4}, word);
        }
        else
            label.setText("Нет слов по вашему запросу");


        add(new Label("Язык " + Application.getInstance().lang.getLang() + " что изображено на картинке (или написано, если картинки нет)"));
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

    private void textRandomize(Button buttons[], Word word){

        int x = (int)(Math.random()*buttons.length);
        buttons[x].setLabel(word.getWord());
        List<Word> words = Application.getInstance().test.getWords();
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
                while(words.get(y).getWord().equals(word.getWord())){
                    y = (int)(Math.random()*buttons.length);
                }
                System.out.println(words.size());
                System.out.println(y);
                buttons[i].setLabel(words.get(y).getWord());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Test test = Application.getInstance().test;

        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }else{
            Stat stat = new Stat();
            stat.setLang(Application.getInstance().lang.getLang());
            stat.setUser(Application.getInstance().loggedUser.getLogin());
            stat.setWord(word.getWord());
            if(word != null && actionEvent.getActionCommand().equals(word.getWord())){
                test.setTrues(test.getTrues()+1);
                stat.setResult("1");
                if(test.getNum() < test.getWords().size()-1){
                    test.setNum(test.getNum()+1);
                    Application.getInstance().frame.move(ProgramFrame.TEST_PAGE);
                }else{
                    label.setIcon(null);
                    label.setText("Тест окончен. Результат: ошибок - " + test.getErrors() + "; Правильно - " + test.getTrues());
                    answer1.removeActionListener(this);
                    answer2.removeActionListener(this);
                    answer3.removeActionListener(this);
                    answer4.removeActionListener(this);
                }

            }else{
                stat.setResult("0");
                test.setErrors(test.getErrors()+1);
                ((Button)actionEvent.getSource()).setBackground(Color.red);
            }
            StatAPI.replaceStat(stat);
        }
    }

}
