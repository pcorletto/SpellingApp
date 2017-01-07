package com.example.android.spellingapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.spellingapp.R;
import com.example.android.spellingapp.model.WordDbHelper;

public class StoreActivity extends ActionBarActivity {

    private EditText wordEditText, imageEditText;
    private Button storeButton, displayButton;

    // Data structures

    Context context;
    WordDbHelper wordDbHelper;
    SQLiteDatabase sqLiteDatabase;

    // Member variables

    private int mWordID;
    private String mWord, mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        wordEditText = (EditText) findViewById(R.id.wordEditText);
        imageEditText = (EditText) findViewById(R.id.imageEditText);
        storeButton = (Button) findViewById(R.id.storeButton);
        displayButton = (Button) findViewById(R.id.displayButton);

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItem(v);
                finish();

            }
        });

    }

    public void addItem(View view) {

        context = this;

        // Perform DB insertion...

        // Initialize wordDbhelper object and SQLiteDatabase object.

        wordDbHelper = new WordDbHelper(context);
        sqLiteDatabase = wordDbHelper.getWritableDatabase();

        mWord = wordEditText.getText().toString();
        mImage = imageEditText.getText().toString();

        // This block only applies if primary key goes from 000 to XXX counter
        // Before inserting the item, retrieve the last value of mWordID (if we have a primary key that
        // goes from 000 to XXX), which is stored in SharedPref file. If there isn't any previous value, assign zero.

        SharedPreferences sharedPreferences = StoreActivity.this
                .getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        mWordID = sharedPreferences.getInt(getString(R.string.WORD_ID),0);

        // Add one to word ID

        mWordID = mWordID + 1;

        // If primary key goes from 000 to XXX counter. Block ends here.

        // Insert the item details in the database
        wordDbHelper.addItem(mWordID, mWord, mImage, sqLiteDatabase);

        Toast.makeText(StoreActivity.this, "Word # " + mWordID + " Saved", Toast.LENGTH_LONG).show();

        // Optional block: applies if primary key goes from 000 to XXX counter.

        // Store new mWordID in SharedPrefs file

        sharedPreferences = StoreActivity.this
                .getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.WORD_ID), mWordID);
        editor.commit();

        // Optional block ends here.

        wordDbHelper.close();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
