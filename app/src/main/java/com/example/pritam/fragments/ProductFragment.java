package com.example.pritam.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.pritam.model.CustomAdapter;
import com.example.pritam.model.Product;
import com.example.pritam.salseapp.R;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    ArrayList dataModels;
    ListView listView;
    private Button add_new_product_next, save_product;
    private View rootView;
    private CustomAdapter adapter;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach ();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_product, container, false);
        add_new_product_next = (Button) rootView.findViewById (R.id.add_new_product_next);
        listView = (ListView) rootView.findViewById (R.id.listView);
        save_product = (Button) rootView.findViewById (R.id.save_product);
        dataModels = new ArrayList ();

        dataModels.add (new Product ("Park Avanue", false));
        dataModels.add (new Product ("Nivia", false));
        dataModels.add (new Product ("Engage", false));
        dataModels.add (new Product ("Axe", false));
        dataModels.add (new Product ("Fog", false));
        dataModels.add (new Product ("Lux", false));
        dataModels.add (new Product ("Patanjali", false));
        dataModels.add (new Product ("Himalaya", false));
        dataModels.add (new Product ("Neo", false));
        dataModels.add (new Product ("Addidas", false));
        dataModels.add (new Product ("Nike", false));
        dataModels.add (new Product ("Reebok", false));
        dataModels.add (new Product ("Apple", false));
        dataModels.add (new Product ("Samsung", false));

        adapter = new CustomAdapter (dataModels, this.getContext ());
        listView.setAdapter (adapter);
        // initilizeList();
        save_product.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                add_new_product_next.setEnabled (true);
                add_new_product_next.setTextColor (Color.WHITE);
                add_new_product_next.setBackgroundColor (getResources ().getColor (R.color.colorPrimary));
                save_product.setVisibility (View.INVISIBLE);
            }
        });
        add_new_product_next.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                swapFragment ();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void initilizeList() {

    }

    private void swapFragment() {
        OTPFragment newOTPFragment = new OTPFragment ();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction ();
        fragmentTransaction.replace (R.id.frame_container, newOTPFragment);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ();
    }
}
