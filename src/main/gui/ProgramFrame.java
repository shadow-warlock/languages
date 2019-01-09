package main.gui;

import dto.objects.User;
import main.Application;
import main.gui.pages.*;
import main.gui.pages.Menu;

import javax.swing.*;
import java.awt.*;


public class ProgramFrame extends JFrame {


    private FrameItem currentItem = null;
    private Dimension dim;

    public static final String LOG_IN = "log_in";
    public static final String REGISTRATION = "registration";
    public static final String GRAMMAR = "grammar";
    public static final String CHANGE_LANG = "change_lang";
    public static final String CHANGE_ACTION = "change_action";
    public static final String CHANGE_CATEGORY = "change_category";
    public static final String CHANGE_CATEGORY_WORDS = "change_category_words";

    public static final String ADD_WORD = "add_word";
    public static final String ADD_CATEGORY = "add_category";
    public static final String TEST_PAGE = "test_page";
    public static final String GRAMMAR_EDIT = "grammar_edit";
    public static final String WORD_LESSON = "word_lesson";
    public static final String WORD_LESSON_EDIT = "word_lesson_edit";
    public static final String ADD_WORD_TO_LESSON = "add_word_to_lesson";
    public static final String WORDS_LOOK = "words_look";
    public static final String CHANGE_LESSON = "change_lesson";
    public static final String GRAMMAR_LOOK = "grammar_look";
    public static final String STATS = "stats";


    public ProgramFrame(){
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)dim.getWidth() - insets.left - insets.right, (int)dim.getHeight() - insets.top - insets.bottom);
        setTitle("Изучение языков");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentItem = new LogIn(dim);
        setContentPane(currentItem);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void move(String action){
        switch (action){
            case STATS:
                remove(currentItem);
                currentItem = new Stats(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case GRAMMAR_LOOK:
                remove(currentItem);
                currentItem = new GrammarLook(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case CHANGE_LESSON:
                remove(currentItem);
                currentItem = new ChangeLesson(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case WORDS_LOOK:
                remove(currentItem);
                currentItem = new WordsLook(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case CHANGE_CATEGORY_WORDS:
                remove(currentItem);
                currentItem = new ChangeCategoryWords(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case ADD_WORD_TO_LESSON:
                remove(currentItem);
                currentItem = new AddWordToLesson(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case WORD_LESSON_EDIT:
                remove(currentItem);
                currentItem = new WordLessonEdit(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case WORD_LESSON:
                remove(currentItem);
                currentItem = new WordLessonsList(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case GRAMMAR_EDIT:
                remove(currentItem);
                currentItem = new GrammarEdit(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
            case GRAMMAR:
                remove(currentItem);
                currentItem = new GrammarList(dim);
                setContentPane(currentItem);
                setVisible(true);
                break;
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
                if(Application.getInstance().loggedUser.getType().equals(User.TEACHER))
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
