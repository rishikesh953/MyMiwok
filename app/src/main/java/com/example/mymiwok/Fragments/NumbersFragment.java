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

public class NumbersFragment extends Fragment {

    private MediaPlayer soundPlayer;

    private AudioManager audioManager ;



    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        soundPlayer.release();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT  || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        soundPlayer.pause();
                        soundPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        soundPlayer.start();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mComplteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMedia();
        }
    };


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_main, container, false);
//
//        // Get a support ActionBar corresponding to this toolbar
//        ActionBar ab = getSupportActionBar();
//
//        // Enable the Up button
//        ab.setDisplayHomeAsUpEnabled(true);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> wordList = new ArrayList<Word>();

        wordList.add(new Word("one", "lutti", R.drawable.number_one,
                R.raw.number_one));
        wordList.add(new Word("two", "ottiko", R.drawable.number_two,
                R.raw.number_two));
        wordList.add(new Word("three", "tolookasu", R.drawable.number_three,
                R.raw.number_three));
        wordList.add(new Word("four", "oyyisa", R.drawable.number_four,
                R.raw.number_four));
        wordList.add(new Word("five", "masokka", R.drawable.number_five,
                R.raw.number_five));
        wordList.add(new Word("six", "temokka", R.drawable.number_six,
                R.raw.number_six));
        wordList.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                R.raw.number_seven));
        wordList.add(new Word("eight", "kawinta", R.drawable.number_eight,
                R.raw.number_eight));
        wordList.add(new Word("nine", "wo'e", R.drawable.number_nine,
                R.raw.number_nine));
        wordList.add(new Word("ten", "na'aacha", R.drawable.number_ten,
                R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), R.color.category_numbers, wordList);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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