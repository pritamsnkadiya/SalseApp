package com.example.pritam.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pritam.salseapp.R;

public class UnverifyFragment extends Fragment {
    public UnverifyFragment() {
        // Required empty public constructor
    }

    public static UnverifyFragment newInstance(String param1, String param2) {
        UnverifyFragment fragment = new UnverifyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unverify, container, false);
    }
}
