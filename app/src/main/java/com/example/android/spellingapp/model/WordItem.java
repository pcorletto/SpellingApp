package com.example.android.spellingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hernandez on 1/1/2017.
 */
public class WordItem implements Parcelable {

    // Private member variables

    // If the item does not have a unique identifier, then create a private variable called “ITEM”_ID,
    // that can be the unique identifier or primary key on the SQLiteDB. In the case of a YouTube Video,
    // it already has a unique video ID. In the case of a Shopping Item, it already has a unique identifier,
    // the UPC Bar Code ID. But sometimes we will not have a unique identifier for the item. Anyway,
    // we MUST create one Item ID, and then later, use an incrementer in the StoreActivity, for when we
    // store a new item in the database. The incrementer number that I will use later will be a number from
    // 001 to my array size minus one (for example, 001 to 499, for a 500-item object array). The
    // incrementer I will use is  called “mItemID”, for example “mExpenseID”. We need to save this
    // “mItemID” on a SharedPreferences file and we need to retrieve it from the SharedPreferences file
    // also.

    private int mWordID;
    private String mWord;
    private String mImage;
    private boolean isSelected;

    // Constructors

    public WordItem(){

    }

    public WordItem(int id, String word, String image){

        this.mWordID = id;
        this.mWord = word;
        this.mImage = image;
        this.isSelected = false;

    }

    // Accessor and mutator methods


    public int getWordID() {
        return mWordID;
    }

    public void setWordID(int wordID) {
        mWordID = wordID;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(mWordID);
        dest.writeString(mWord);
        dest.writeString(mImage);

    }

    private WordItem(Parcel in){

        mWordID = in.readInt();
        mWord = in.readString();
        mImage = in.readString();

    }

    public static final Creator<WordItem>CREATOR = new Creator<WordItem>(){


        @Override
        public WordItem createFromParcel(Parcel source) {
            return new WordItem(source);
        }

        @Override
        public WordItem[] newArray(int size) {
            return new WordItem[size];
        }
    };

}
