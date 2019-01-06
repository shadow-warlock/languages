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




    public AddWordToLesson(Dimension dim){
        super(dim, 7, 1);
        addWord = new Button("Добавить");
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
        JPanel addRm = new JPanel(new GridLayout(1, 2));
        addRm.add(addWord);
        addRm.add(removeWord);
        add(addRm);
        addRm.setFont(font);
        add(back);

        setFont(font);
        buttons.setFont(font);
        buttons2.setFont(font);
        buttons3.setFont(font);
        category.setFont(font);
        if(Application.getInstance().currentEditWord != null){
            Database db = new Database();
            ResultSet wordData = db.select("SELECT * FROM words WHERE word = '"+Application.getInstance().currentEditWord+"' AND lang = '"+Application.getInstance().lang+"' AND user = '"+Application.getInstance().user+"'");

            try {
                wordData.next();
                img = new File(wordData.getString("img"));
                translate.setText(wordData.getString("translate"));
                transcription.setText(wordData.getString("transcription"));
                example.setText(wordData.getString("example"));
                word.setText(wordData.getString("word"));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addWord){
            if(Application.getInstance().currentEditWord == null){
                Database db = new Database();
                db.insert("INSERT INTO words VALUES('"+Application.getInstance().user+"', \""+Application.getInstance().lang+"\", \'"+word.getText()+"\', \'"+translate.getText()+"\', \'"+transcription.getText()+"\', \'"+(img==null?"null":img.getName())+"\', \'"+example.getText()+"\', \'"+category.getSelectedItem()+"\')");
                db.insert("INSERT INTO lessons_words VALUES('"+Application.getInstance().wordLessonCurrentName+"', '"+word.getText()+"')");
                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
            }else{
                Database db = new Database();
                db.insert("UPDATE words SET word=\'"+word.getText()+"\', translate=\'"+translate.getText()+"\', transcription=\'"+transcription.getText()+"\', img=\'"+(img==null?"null":img.getName())+"\', example=\'"+example.getText()+"\', category=\'"+category.getSelectedItem()+"\' WHERE word = '"+Application.getInstance().currentEditWord+"' AND lang='"+Application.getInstance().lang+"' AND user='"+Application.getInstance().user+"'");
                db.insert("UPDATE lessons_words SET word='"+word.getText()+"' WHERE lesson='"+Application.getInstance().wordLessonCurrentName+"' AND word= '"+Application.getInstance().currentEditWord+"'");
                Application.getInstance().currentEditWord = null;
                Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);
            }

        }
        if(actionEvent.getSource() == back){
            Application.getInstance().frame.move(ProgramFrame.WORD_LESSON_EDIT);

        }
        if(actionEvent.getSource() == removeWord){
            Database db = new Database();
            db.insert("DELETE FROM words WHERE word='"+word.getText()+"' AND lang = '"+Application.getInstance().lang+"' AND user='"+Application.getInstance().user+"'");
            db.insert("DELETE FROM lessons_words WHERE lesson='"+Application.getInstance().wordLessonCurrentName+"' AND word='"+word.getText()+"'");
            Application.getInstance().frame.move(ProgramFrame.CHANGE_ACTION);

        }
    }
}
