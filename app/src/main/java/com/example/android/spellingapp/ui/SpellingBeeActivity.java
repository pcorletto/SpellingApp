package com.example.android.spellingapp.ui;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.spellingapp.R;
import com.example.android.spellingapp.model.ReloadListFromDB;
import com.example.android.spellingapp.model.WordList;

import java.util.Locale;
import java.util.Random;

public class SpellingBeeActivity extends ActionBarActivity {

    private ImageView image;
    private TextView wordTextView;
    private ImageButton rewindButton, backButton, playButton, nextButton, forwardButton;

    private WordList mWordList = new WordList();
    ReloadListFromDB reloadedList = new ReloadListFromDB();
    MediaPlayer player;

    String randomWord, currentLetter, builderStr;

    int randomNumber, letterPosition, wordPosition;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_bee);

        image = (ImageView) findViewById(R.id.image);
        wordTextView = (TextView) findViewById(R.id.wordTextView);
        rewindButton = (ImageButton) findViewById(R.id.rewindButton);
        backButton = (ImageButton) findViewById(R.id.backButton);
        playButton = (ImageButton) findViewById(R.id.playButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        forwardButton = (ImageButton) findViewById(R.id.forwardButton);

        String searchItem = "";

        letterPosition = 0;

        wordPosition = 0;

        randomWord = "";

        currentLetter = "";

        tts=new TextToSpeech(SpellingBeeActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        //ConvertTextToSpeech();
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

        mWordList = reloadedList.reloadListFromDB("get", searchItem, getApplicationContext());

        int listSize = reloadedList.getListSize();

        Random r = new Random();
        randomNumber = r.nextInt(listSize - 1);

        // Pick a random word from the word list in DB

        randomWord = mWordList.mWordItem[randomNumber].getWord();

        // Highlight and display the current letter being spelled

        highlightCurrentLetter(randomWord, letterPosition);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                letterPosition++;

                if (letterPosition > randomWord.length() - 1) {

                    if(tts!=null){
                        tts.stop();
                    }

                    letterPosition = randomWord.length() - 1;
                    tts.setLanguage(Locale.US);
                    tts.speak(randomWord, TextToSpeech.QUEUE_ADD, null);

                }

                else {

                    // Highlight and display the current letter being spelled

                    highlightCurrentLetter(randomWord, letterPosition);

                    currentLetter = randomWord.substring(letterPosition, letterPosition + 1);
                    playLetterSound(currentLetter);

                }

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Highlight and display the current letter being spelled

                highlightCurrentLetter(randomWord, letterPosition);

                currentLetter = randomWord.substring(letterPosition, letterPosition + 1);
                playLetterSound(currentLetter);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(letterPosition <= 0){

                    letterPosition = 0;

                }

                else{

                    letterPosition--;

                }

                // Highlight and display the current letter being spelled

                highlightCurrentLetter(randomWord, letterPosition);

                currentLetter = randomWord.substring(letterPosition, letterPosition + 1);
                playLetterSound(currentLetter);

            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wordPosition--;

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.magicwand);
                player.start();

                if(wordPosition<=0){

                    wordPosition = randomWord.length()-1;

                }

                if(wordPosition==randomWord.length()-1){

                    wordPosition = 0;

                }

                randomWord = mWordList.mWordItem[wordPosition].getWord();

                letterPosition = 0;

                // Highlight and display the current letter being spelled

                highlightCurrentLetter(randomWord, letterPosition);

            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.magicwand);
                player.start();

                wordPosition++;

                if(wordPosition>reloadedList.getListSize()-1){

                    wordPosition--;

                }

                randomWord = mWordList.mWordItem[wordPosition].getWord();

                letterPosition = 0;

                // Highlight and display the current letter being spelled

                highlightCurrentLetter(randomWord, letterPosition);

            }
        });

    }

    public void playLetterSound(String letter){

        switch (letter){

            case "a":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.a);
                player.start();
                break;

            }

            case "b":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.b);
                player.start();
                break;

            }

            case "c":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.c);
                player.start();
                break;

            }

            case "d":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.d);
                player.start();
                break;

            }

            case "e":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.e);
                player.start();
                break;

            }

            case "f":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.f);
                player.start();
                break;

            }

            case "g":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.g);
                player.start();
                break;
            }

            case "h":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.h);
                player.start();
                break;

            }

            case "i":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.i);
                player.start();
                break;

            }

            case "j":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.j);
                player.start();
                break;

            }

            case "k":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.k);
                player.start();
                break;

            }

            case "l":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.l);
                player.start();
                break;
            }

            case "m":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.m);
                player.start();
                break;

            }

            case "n":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.n);
                player.start();
                break;

            }

            case "o":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.o);
                player.start();
                break;

            }

            case "p":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.p);
                player.start();
                break;

            }

            case "q":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.q);
                player.start();
                break;

            }

            case "r":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.r);
                player.start();
                break;

            }

            case "s":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.s);
                player.start();
                break;

            }

            case "t":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.t);
                player.start();
                break;

            }

            case "u":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.u);
                player.start();
                break;
            }

            case "v":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.v);
                player.start();
                break;

            }

            case "w":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.w);
                player.start();
                break;

            }

            case "x":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.x);
                player.start();
                break;

            }

            case "y":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.y);
                player.start();
                break;

            }

            case "z":{

                if(player!=null){
                    player.release();
                }
                player = MediaPlayer.create(getApplicationContext(), R.raw.z);
                player.start();
                break;

            }


        }

    }

    public void highlightCurrentLetter(String word, int position){

        builderStr = "";

        for(int i=0; i<word.length(); i++){

            if(i==position){

                builderStr += "<font color='blue'>" + word.substring(position, position+1) + "</font>";
            }

            else{

                builderStr += word.substring(i, i+1);
            }
        }

        // Display this random Word in the TextView

        wordTextView.setText(Html.fromHtml(builderStr), TextView.BufferType.SPANNABLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spelling_bee, menu);
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

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
