package main;

import dto.objects.*;
import main.client.Client;
import main.gui.ProgramFrame;

import java.util.ArrayList;
import java.util.List;


public class Application {

    public Client client;
    public User loggedUser;
    public Language lang;

    public GrammarLesson currentGrammarLesson = null;
    public WordLesson currentWordLesson = null;
    public Word currentEditWord = null;
//    public String user = null;
//    public UserType userType = null;
//    public String lang = null;
//    public String action = null;
    public ProgramFrame frame;
    public Test test;
//    public String category;
    public List<Word> looksWords = null;
//
//    int wordNum = 0;
//    int errors = 0;
//    int trues = 0;

    private static Application self;

    private Application(){}

    public static Application getInstance(){
        if(self == null){
            self = new Application();
            self.client = new Client();
        }
        return self;
    }
}
