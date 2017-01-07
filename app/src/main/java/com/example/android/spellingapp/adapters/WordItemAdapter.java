package com.example.android.spellingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.android.spellingapp.R;
import com.example.android.spellingapp.model.WordItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hernandez on 1/5/2017.
 */
public class WordItemAdapter extends ArrayAdapter<WordItem> {

    private List<WordItem> list;

    ArrayList<Boolean> positionArray;

    private Context mContext;

    public WordItemAdapter(Context context, List<WordItem> list) {
        super(context, R.layout.word_list_item, list);
        this.mContext = context;
        this.list = list;

        positionArray = new ArrayList<Boolean>(list.size());
        for(int i = 0; i < list.size();i++){
            positionArray.add(false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public WordItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        WordItem wordItem = list.get(position);

        if(convertView == null){
            //brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.word_list_item, null);

            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.wordEditText = (EditText) convertView.findViewById(R.id.wordEditText);

            convertView.setTag(holder);

        }

        else{
            // We have these views set up.
            holder = (ViewHolder) convertView.getTag();
            holder.checkBox.setOnCheckedChangeListener(null);

        }

        // Now, set the data:

        holder.word = this.getItem(position).getWord();
        holder.wordEditText.setText(holder.word);

        holder.checkBox.setFocusable(false);
        holder.checkBox.setChecked(positionArray.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked ){

                    positionArray.add(position, true);
                    list.get(position).setSelected(true);

                }else {

                    positionArray.add(position, false);
                    list.get(position).setSelected(false);

                }

            }
        });

        return convertView;
    }

    public void refresh(List<WordItem> list){

        this.list = list;
        notifyDataSetChanged();

    }

    private static class ViewHolder{

        String word;
        CheckBox checkBox;
        EditText wordEditText;

    }


}
