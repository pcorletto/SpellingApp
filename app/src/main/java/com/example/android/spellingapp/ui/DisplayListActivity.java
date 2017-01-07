package com.example.android.spellingapp.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.android.spellingapp.R;
import com.example.android.spellingapp.adapters.WordItemAdapter;
import com.example.android.spellingapp.model.ReloadListFromDB;
import com.example.android.spellingapp.model.WordDbHelper;
import com.example.android.spellingapp.model.WordItem;
import com.example.android.spellingapp.model.WordList;

import java.util.ArrayList;
import java.util.List;

public class DisplayListActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private ListView listview;
    private List<WordItem> list = new ArrayList<>();

    private WordItemAdapter mAdapter;
    private WordList mWordList = new WordList();
    private WordDbHelper wordDbHelper;
    ReloadListFromDB reloadedList = new ReloadListFromDB();
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        // Get the toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_list);
        getSupportActionBar().setTitle("Words");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        listview = (ListView) findViewById(android.R.id.list);

        String searchItem = "";

        mWordList = reloadedList.reloadListFromDB("sort", searchItem, getApplicationContext());

        for(int i=0; i<reloadedList.getListSize(); i++){

            list.add(mWordList.mWordItem[i]);

        }

        mAdapter = new WordItemAdapter(DisplayListActivity.this, list);

        mAdapter.notifyDataSetChanged();

        listview.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_list, menu);
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

        if (id == R.id.action_home){

            Intent intent = new Intent(DisplayListActivity.this, MainActivity.class);
            startActivity(intent);

        }

        if (id == R.id.action_delete){

            // Initialize the purchaseDBHelper object

            wordDbHelper = new WordDbHelper(getApplicationContext());

            // Initialize the SQLiteDatabase object

            sqLiteDatabase = wordDbHelper.getReadableDatabase();


            for(int i=0; i<list.size(); i++) {

                if(list.get(i).isSelected()){

                    // For SQLiteDatabase: Delete this item here, if checked.

                    String item_for_DB_deletion = list.get(i).getWordID() + "";

                    // Delete the word item from the SQLite database

                    wordDbHelper.deleteWordItem(item_for_DB_deletion, sqLiteDatabase);

                    Intent intent = new Intent(DisplayListActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
