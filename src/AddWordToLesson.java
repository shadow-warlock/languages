import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class AddWordToLesson extends FrameItem implements ActionListener{
    private Button addWord;
    private Button back;
    private Button addImage;

    private JLabel label;

    private File img;
    private TextField word;
    private TextField translate;
    private TextField transcription;
    private TextField example;
    private JComboBox category;




    public AddWordToLesson(Dimension dim){
        super(dim, 7, 1);
        addWord = new Button("Добавить");
        addWord.addActionListener(this);
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
        Database db = new Database();
        String[] categories = new String[1];
        categories[0] = Application.getInstance().wordLessonCurrentName;
        category = new JComboBox(categories);

        add(new Label("Добавьте слово. Язык выбран " + Application.getInstance().lang));
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
        add(addWord);
        add(back);

        setFont(font);
        buttons.setFont(font);
        buttons2.setFont(font);
        buttons3.setFont(font);
        category.setFont(font);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addWord){
            Database db = new Database();
            db.insert("INSERT INTO words VALUES('"+Application.getInstance().user+"', \""+Application.getInstance().lang+"\", \'"+word.getText()+"\', \'"+translate.getText()+"\', \'"+transcription.getText()+"\', \'"+(img==null?"null":img.getName())+"\', \'"+example.getText()+"\', \'"+category.getSelectedItem()+"\')");
            db.insert("INSERT INTO lessons_words VALUES('"+Application.getInstance().wordLessonCurrentName+"', '"+word.getText()+"')");
            Application.getInstance().action = null;
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);

        }
    }
}