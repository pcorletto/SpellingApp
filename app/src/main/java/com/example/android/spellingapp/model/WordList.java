package com.example.android.spellingapp.model;

/**
 * Created by hernandez on 1/1/2017.
 */
public class WordList {

    // The class WordList is only going to hold 1,000 words
    // stored on the WordListDB, which is an
    // SQLite database of words you store. However, WordList is an array of WordItem
    // objects that only holds the 1,000 words.

    public WordItem[] mWordItem = new WordItem[1000];

    public void addWordItem(WordItem wordItem, int rowNumber){

        mWordItem[rowNumber] = wordItem;

    }

    public String getWordItem(int i){
        return mWordItem[i].getWord() +
                " " + mWordItem[i].getImage()+ "\n\n";
    }

}
