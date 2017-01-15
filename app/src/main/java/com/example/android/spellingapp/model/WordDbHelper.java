package com.example.android.spellingapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hernandez on 1/1/2017.
 */
public class WordDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WORDLIST.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY = "CREATE TABLE " + WordListDB.NewWordItem.TABLE_NAME +
            "(" + WordListDB.NewWordItem.WORD_ID + " INTEGER," +
            WordListDB.NewWordItem.WORD + " TEXT," +
            WordListDB.NewWordItem.IMAGE + " TEXT);";

    // Default Constructor:

    public WordDbHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created / opened ...");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created ...");

    }

    // Insert the item next. Method for inserting the word item.

    public void addItem(int id, String word, String image, SQLiteDatabase db){

        // Map key-values

        ContentValues contentValues = new ContentValues();
        contentValues.put(WordListDB.NewWordItem.WORD_ID, id);
        contentValues.put(WordListDB.NewWordItem.WORD, word);
        contentValues.put(WordListDB.NewWordItem.IMAGE, image);

        // Save all these into the database

        db.insert(WordListDB.NewWordItem.TABLE_NAME, null, contentValues);

        Log.e("DATABASE OPERATIONS", "One row is inserted ...");

    }

    public Cursor getWordItem(SQLiteDatabase db){

        // The return type of Object is "Cursor"
        Cursor cursor;

        // Create projections, or the needed column names
        String[] projections = {WordListDB.NewWordItem.WORD_ID,
                WordListDB.NewWordItem.WORD,
                WordListDB.NewWordItem.IMAGE};

        // We only need the table name and projection parameters. No conditions will be specified,
        // so, we will pass in null for the last five parameters.

        cursor = db.query(WordListDB.NewWordItem.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;

    }

    public Cursor searchWordItems(String searchItem, SQLiteDatabase db){

        // The return type of Object is "Cursor"
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " +
                WordListDB.NewWordItem.TABLE_NAME + " where " +WordListDB.NewWordItem.WORD+ " like '"
                +searchItem+"%'" + " ORDER BY " + WordListDB.NewWordItem.WORD + " COLLATE NOCASE ASC", null);
        return cursor;
    }

    public Cursor sortWordItems(SQLiteDatabase db){

        Cursor cursor;

        // Modified this method so that the results are sorted in alphabetical ascending order,
        // by the word. It worked!!!

        cursor = db.rawQuery("SELECT * FROM " + WordListDB.NewWordItem.TABLE_NAME +
                " ORDER BY " + WordListDB.NewWordItem.WORD + " COLLATE NOCASE ASC", null);

        // Another valid way to sort it, case insensitive, in SQL is as follows:
        // cursor = db.rawQuery("SELECT * FROM " + EntryListDB.NewEntryItem.TABLE_NAME +
        //        " ORDER BY LOWER(" + EntryListDB.NewEntryItem.WORD + ") ASC", null);

        return cursor;

    }

    public Cursor randomizeWordItems(SQLiteDatabase db){

        Cursor cursor1, cursor2;

        cursor1 = db.rawQuery("SELECT * FROM " + WordListDB.NewWordItem.TABLE_NAME, null);

        int count = cursor1.getCount();

        count = count / 2;

        cursor2 = db.rawQuery("SELECT * FROM " + WordListDB.NewWordItem.TABLE_NAME +
                " WHERE " + WordListDB.NewWordItem.WORD_ID + " IN (SELECT " +
                WordListDB.NewWordItem.WORD_ID + " FROM " + WordListDB.NewWordItem.TABLE_NAME
                + " ORDER BY RANDOM() LIMIT " + count + ")", null);

        return cursor2;

    }



    public void updateWord(String id, String word, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        // THE NEXT FIELD, "DEFINITION", NEEDS TO BE “PUT”. THIS
        //  IS THE ONE THAT I AM UPDATING IN THE DB
        contentValues.put(WordListDB.NewWordItem.WORD, word);

        // Provide a condition or selection

        String selection = WordListDB.NewWordItem.WORD + " LIKE ?";

        String[] selection_args = {id};

        sqLiteDatabase.update(WordListDB.NewWordItem.TABLE_NAME, contentValues,
                selection, selection_args);

    }


    public void deleteWordItem(String id, SQLiteDatabase sqLiteDatabase){

        String selection = WordListDB.NewWordItem.WORD_ID + " LIKE ?";

// Use the primary key, or the Item ID (which is stored and retrieved from a SharedPreferences file), for
// selecting the item to be deleted from the DB. This
// will ensure that we will only delete the selected item(s) and not anything else.

        String[] selection_args = {id};

        sqLiteDatabase.delete(WordListDB.NewWordItem.TABLE_NAME, selection, selection_args);

    }
    /* THE SAMPLE CODE BELOW IS IF WE NEED TO UPDATE INFORMATION FROM ANY OF OUR
// DB FIELDS. IN THIS EXAMPLE, I UPDATED THE SUBTOTAL. ADAPT IT THE NEEDS OF THE APP
    public int updateSubtotal(String product_name, String quantity, String subtotal, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        // THE NEXT FOLLOWING TWO FIELDS, QUANTITY AND SUBTOTAL NEED TO BE “PUT”. THESE
        // ARE THE ONES THAT I AM UPDATING IN THE DB
        contentValues.put(ShoppingList.NewShoppingItem.QUANTITY, quantity);
        contentValues.put(ShoppingList.NewShoppingItem.SUBTOTAL, subtotal);

        // Provide a condition or selection

        String selection = ShoppingList.NewShoppingItem.PRODUCT_NAME + " LIKE ?";

        String[] selection_args = {product_name};

        int count = sqLiteDatabase.update(ShoppingList.NewShoppingItem.TABLE_NAME, contentValues,
                selection, selection_args);

        return count;
    }*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
