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

public class FamilyFragment extends Fragment {

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


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.word_main, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> wordList = new ArrayList<>();

        wordList.add(new Word("father","әpә",R.drawable.family_father, R.raw.family_father));
        wordList.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        wordList.add(new Word("son","angsi",R.drawable.family_son, R.raw.family_son));
        wordList.add(new Word("daughter","tune",R.drawable.family_daughter, R.raw.family_daughter));
        wordList.add(new Word("older brother","taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
        wordList.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        wordList.add(new Word("older sister", "teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
        wordList.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        wordList.add(new Word("grandfather","ama",R.drawable.family_grandfather, R.raw.family_grandfather));
        wordList.add(new Word("grandmother","paapa",R.drawable.family_grandmother, R.raw.family_mother));

        WordAdapter adapter = new WordAdapter(getActivity(), R.color.category_family, wordList);
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