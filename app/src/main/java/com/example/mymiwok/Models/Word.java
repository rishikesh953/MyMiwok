package com.example.mymiwok.Models;

import android.content.Context;

public class Word {


    private final String mDefaultTranslation;

    private final String mMiwokTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private final int mSoundId;

    public Word(String defaultTranslation, String miwokTranslation,
                int ImageResourceId,
                int SoundID) {

        mDefaultTranslation = defaultTranslation;
        mImageResourceId = ImageResourceId;
        mMiwokTranslation = miwokTranslation;
        mSoundId = SoundID;
    }


    public Word(String defaultTranslation, String miwokTranslation, int SoundID) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSoundId = SoundID;
    }


    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public  int getImageResourceId() {
        return mImageResourceId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public boolean hasImage(){ return mImageResourceId != NO_IMAGE_PROVIDED;}


    public int getSoundId(){ return mSoundId; }
}
