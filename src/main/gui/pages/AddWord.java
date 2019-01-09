package main.gui.pages;

import dto.objects.Category;
import dto.objects.Word;
import main.Application;
import main.api.CategoryAPI;
import main.api.WordAPI;
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
import java.nio.file.Files;


public class AddWord extends FrameItem implements ActionListener{
    private Button addWord;
    private Button removeWord;
    private Button back;
    private Button addImage;

    private JLabel label;

    private File img;
    private TextField word;
    private TextField translate;
    private TextField transcription;
    private TextField example;
    private JComboBox category;




    public AddWord(Dimension dim){
        super(dim, 7, 1);
        addWord = new Button("Добавить\\изменить");
        addWord.addActionListener(this);
        removeWord = new Button("Удалить");
        removeWord.addActionListener(this);
        back = new Button("Назад");
        back.addActionListener(this);
        label = new JLabel();
        addImage = new Button("Выбрать картинку");
        addImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    File newF = new File("imgs/" + file.getName());
                    try {
                        if(!newF.exists())
                            Files.copy(file.toPath(), newF.toPath());
                        img = newF;
                        try {
                            BufferedImage img = ImageIO.read(newF);
                            label.setIcon(new ImageIcon(img));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        word = new TextField();
        translate = new TextField();
        transcription = new TextField();
        example = new TextField();
        java.util.List<Category> list = CategoryAPI.getCategoriesByLangAndUser(Application.getInstance().lang.getLang(), Application.getInstance().loggedUser.getLogin());
        String[] categories = new String[list.size()+1];
        categories[0] = "Без категории";
        for (int i=1; i< list.size(); i++){
            categories[i] = list.get(i-1).getTitle();
        }
        category = new JComboBox(categories);

        add(new Label("Добавьте\\измените слово. Язык выбран " + Application.getInstance().lang));
        add(label);
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        JPanel buttons2 = new JPanel(new GridLayout(2, 2));
        JPanel buttons3 = new JPanel(new GridLayout(2, 2));

        buttons.add(new Label("Картинка"));
        buttons.add(addImage);
        buttons.add(new Label("Слово"));
        buttons.add(word);
        buttons2.add(new Label("Перевод"));
        buttons2.add(translate);
        buttons2.add(new Label("Транскрипция"));
        buttons2.add(transcription);
        buttons3.add(new Label("Пример\\фраза"));
        buttons3.add(example);
        buttons3.add(new Label("Категория"));
        buttons3.add(category);
        add(buttons);
        add(buttons2);
        add(buttons3);
        JPanel addRm = new JPanel(new GridLayout(1, 2));
        addRm.add(addWord);
        addRm.add(removeWord);
        add(addRm);
//        add(addWord);
        add(back);
        addRm.setFont(font);
        setFont(font);
        buttons.setFont(font);
        buttons2.setFont(font);
        buttons3.setFont(font);
        category.setFont(font);
        Word editWord = Application.getInstance().currentEditWord;
        if(editWord != null){
            img = new File(editWord.getImg());
            if(img.exists()){
                try {
                    BufferedImage img2 = ImageIO.read(img);
                    label.setIcon(new ImageIcon(img2));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            translate.setText(editWord.getTranslate());
            transcription.setText(editWord.getTranscription());
            example.setText(editWord.getExample());
            word.setText(editWord.getWord());
            category.setSelectedItem(editWord.getCategory());
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Word addedWord = new Word();
        if(!category.getSelectedItem().equals("Без категории")){
            addedWord.setCategory((String) category.getSelectedItem());
        }
        addedWord.setWord(word.getText());
        addedWord.setTranscription(transcription.getText());
        addedWord.setTranslate(translate.getText());
        addedWord.setImg(img !=null?img.getName():"null");
        addedWord.setExample(example.getText());
        addedWord.setUser(Application.getInstance().loggedUser.getLogin());
        addedWord.setLang(Application.getInstance().lang.getLang());
        if(actionEvent.getSource() == addWord){
            WordAPI.replaceWord(addedWord);
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
        if(actionEvent.getSource() == removeWord){
            WordAPI.deleteWord(addedWord);
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }
}
