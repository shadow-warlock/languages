package dto.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test{
    private List<Word> words = new ArrayList<>();
    private int errors;
    private int num;
    private int trues;

    public void addWord(Word word){
        words.add(word);
    }

    public int getTrues() {
        return trues;
    }

    public void setTrues(int trues) {
        this.trues = trues;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
