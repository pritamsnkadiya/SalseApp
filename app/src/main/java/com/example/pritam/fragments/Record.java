package com.example.pritam.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pritam.salseapp.R;

import java.util.ArrayList;
import java.util.List;

public class Record extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View rootView;

    public Record() {
        // Required empty public constructor
    }

    public static Record newInstance(String param1, String param2) {
        Record fragment = new Record ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume ();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_record, container, false);
        viewPager = (ViewPager) rootView.findViewById (R.id.viewPager_tab);
        setupViewPager (viewPager);

        tabLayout = (TabLayout) rootView.findViewById (R.id.tabs);
        tabLayout.setupWithViewPager (viewPager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter (this.getFragmentManager ());
        adapter.addFragment (new UnverifyFragment (), "Unverify");
        adapter.addFragment (new VerifyFragment (), "Verify");
        viewPager.setAdapter (adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<> ();
        private final List<String> mFragmentTitleList = new ArrayList<> ();

        public ViewPagerAdapter(FragmentManager manager) {
            super (manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get (position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size ();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add (fragment);
            mFragmentTitleList.add (title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get (position);
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager ().beginTransaction ();
        transaction.replace (R.id.frame_container, fragment);
        transaction.addToBackStack (null);
        transaction.commit ();
    }
}
