package com.example.mymiwok.Adapters;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import com.example.mymiwok.R;
import com.example.mymiwok.Models.Word;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    MediaPlayer soundPlayer ;

    private static int  BackGroundID;


    public WordAdapter (Activity context,int BackGroundColorID, ArrayList<Word> wordList)
    {


        super(context,0, wordList);
        BackGroundID = BackGroundColorID;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }


            Word wordl = getItem(position);

            TextView nameTextView = listItemView.findViewById(R.id.miwok_name);

            nameTextView.setText(wordl.getMiwokTranslation());


            ImageView imageView = listItemView.findViewById(R.id.image2);

        if (wordl.hasImage()) {

            imageView.setImageResource(wordl.getImageResourceId());

            imageView.setVisibility(View.VISIBLE);
        } else {

            imageView.setVisibility(View.GONE);
        }


            TextView numberTextView = listItemView.findViewById(R.id.english_name);

            numberTextView.setText(wordl.getDefaultTranslation());
            

            View textContainer = listItemView.findViewById(R.id.text_container);
            int color = ContextCompat.getColor(getContext(), BackGroundID);
            textContainer.setBackgroundColor(color);
            
            

           ImageView soundImageView = listItemView.findViewById(R.id.play_button);
            soundImageView.setBackgroundColor(color);

            return listItemView;


    }
}
