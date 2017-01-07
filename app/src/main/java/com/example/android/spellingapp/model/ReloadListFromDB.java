package com.example.android.spellingapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hernandez on 1/5/2017.
 */
public class ReloadListFromDB {

    // Data structures

    private WordItem mWordItem;
    private int mRowNumber;
    private WordList mWordList = new WordList();

    WordDbHelper wordDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    public ReloadListFromDB(){

    }

    public WordList reloadListFromDB(String selector, String searchItem, Context context){

        // Initialize word item
        mWordItem = new WordItem();

        // Initialize WordDbHelper and SQLiteDB
        wordDbHelper = new WordDbHelper(context);
        sqLiteDatabase = wordDbHelper.getReadableDatabase();

        if (selector.equals("search")) {

            cursor = wordDbHelper.searchWordItems(searchItem, sqLiteDatabase);

        }

        else if (selector.equals("get")){

            cursor = wordDbHelper.getWordItem(sqLiteDatabase);
        }
        else if (selector.equals("sort")){

            cursor = wordDbHelper.sortWordItems(sqLiteDatabase);
        }

        // Initialize the row number

        mRowNumber = 0;

        if(cursor.moveToFirst()){

            do{
                int word_ID;
                String word, image;

                word_ID = cursor.getInt(0);
                word = cursor.getString(1);
                image = cursor.getString(2);

                mWordItem = new WordItem(word_ID, word, image);

                mWordList.addWordItem(mWordItem, mRowNumber);

                mRowNumber++;

            }

            while(cursor.moveToNext());
        }

        return mWordList;

    }

    public int getListSize(){

        // This method is used to count the number of items in the reloaded list

        // Initialize the row number

        mRowNumber = 0;

        if(cursor.moveToFirst()){

            do{

                mRowNumber++;

            }

            while(cursor.moveToNext());
        }

        return mRowNumber;
    }

}
