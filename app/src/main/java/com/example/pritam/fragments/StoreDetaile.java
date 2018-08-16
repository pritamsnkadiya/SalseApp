package com.example.pritam.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pritam.salseapp.R;
public class StoreDetaile extends Fragment {
    public StoreDetaile() {
        // Required empty public constructor
    }

    public static StoreDetaile newInstance(String param1, String param2) {
        StoreDetaile fragment = new StoreDetaile();
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
        return inflater.inflate(R.layout.fragment_store_detaile, container, false);
    }
}
