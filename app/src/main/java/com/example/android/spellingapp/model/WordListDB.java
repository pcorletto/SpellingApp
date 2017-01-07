package com.example.android.spellingapp.model;

/**
 * Created by hernandez on 1/1/2017.
 */
public class WordListDB {

    // In the class WordListDB a user can store as many words as
    // he or she would like. However, WordList only holds 1,000 words.

    public static abstract class NewWordItem{

        // WORD_ID is the primary key. If the item does not have a unique identifier, we must use an Item
        // ID. Later, use the “ROW_NUMBER” incrementer to assign a value to this Item ID or primary key.
        // This “ROW_NUMBER” needs to be stored and retrieved from a SharedPreferences file.

        public static final String WORD_ID = "word_id";
        public static final String WORD = "word";
        public static final String IMAGE = "image";

        // Table name

        public static final String TABLE_NAME = "word_list";

    }


}
