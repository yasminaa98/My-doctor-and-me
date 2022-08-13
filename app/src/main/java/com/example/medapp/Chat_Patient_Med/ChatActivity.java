package com.example.medapp.Chat_Patient_Med;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.example.medapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.view);
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ChatFragment(),"DM");
        viewPagerAdapter.addFragment(new UsersFragment(),"Doctors");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}