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

public class PhrasesFragment extends Fragment {

    private MediaPlayer soundPlayer;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
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


    public PhrasesFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.word_main, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> wordList = new ArrayList<Word>();

        wordList.add(new Word("Where are you going?","minto wuksus",
                R.raw.phrase_where_are_you_going));
        wordList.add(new Word("What is your name?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        wordList.add(new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        wordList.add(new Word("How are you feeling?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        wordList.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        wordList.add(new Word("Are you coming?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        wordList.add(new Word("Yes, I’m coming.","hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        wordList.add(new Word("I’m coming.","әәnәm", R.raw.phrase_im_coming));
        wordList.add(new Word("Let’s go.","yoowutis", R.raw.phrase_lets_go));
        wordList.add(new Word("Come here.","әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(),R.color.category_phrases, wordList );
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word sound = wordList.get(position);

                releaseMedia();

                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
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