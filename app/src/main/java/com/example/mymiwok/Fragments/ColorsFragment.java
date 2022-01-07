package com.example.mymiwok.Fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mymiwok.R;
import com.example.mymiwok.Models.Word;
import com.example.mymiwok.Adapters.WordAdapter;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {

    private MediaPlayer soundPlayer;

    private AudioManager audioManager;

    private  AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if( focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                soundPlayer.start();
            }else if( focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                soundPlayer.release();
            }else if( focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                soundPlayer.pause();
                soundPlayer.seekTo(0);
            }
        }
    };

    private MediaPlayer.OnCompletionListener mComplteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMedia();
        }
    };



    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_main, container, false);


        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> wordList = new ArrayList<Word>();

        wordList.add(new Word("red","weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        wordList.add(new Word("green", "chokokki",R.drawable.color_green, R.raw.color_green));
        wordList.add(new Word("brown","ṭakaakki",R.drawable.color_brown, R.raw.color_brown));
        wordList.add(new Word("gray","ṭopoppi",R.drawable.color_gray, R.raw.color_gray));
        wordList.add(new Word("black","kululli",R.drawable.color_black, R.raw.color_black));
        wordList.add(new Word("white","kelelli",R.drawable.color_white, R.raw.color_white));
        wordList.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        wordList.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(getActivity(), R.color.category_colors, wordList );
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word sound = wordList.get(position);

                releaseMedia();

                int result = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if( result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    soundPlayer = MediaPlayer.create(getActivity(), sound.getSoundId());

                    soundPlayer.start();

                    soundPlayer.setOnCompletionListener(mComplteListener);
                }



            }
        });

        return rootView;
    }

    private void releaseMedia() {
        if (soundPlayer != null) {
            soundPlayer.release();
            soundPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMedia();
    }
}