package com.example.mymiwok.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mymiwok.Fragments.ColorsFragment;
import com.example.mymiwok.Fragments.FamilyFragment;
import com.example.mymiwok.Fragments.NumbersFragment;
import com.example.mymiwok.Fragments.PhrasesFragment;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Numbers", "Family", "Colors", "Phrases" };

    public CategoryFragmentPagerAdapter(FragmentManager fm) {
       super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if( position == 0)
        {
            return new NumbersFragment();
        }else if( position == 1)
        {
            return  new FamilyFragment();
        }else if( position == 2)
        {
            return new ColorsFragment();
        }
        else
        {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
