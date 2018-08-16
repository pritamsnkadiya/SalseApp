package com.example.pritam.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.pritam.salseapp.R;

public class AddNew extends Fragment implements View.OnClickListener {

    private Button add_new_next_details, save_store;
    private EditText store_name, store_no, floor, address, phone_no, phone_no2;
    private View rootView;
    private AwesomeValidation awesomeValidation;

    public AddNew() {
        // Required empty public constructor
    }

    @Override
    public void onDetach() {
        super.onDetach ();
    }

    public static AddNew newInstance(String param1, String param2) {
        AddNew fragment = new AddNew ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume ();
        addValidationToViews ();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_add_new, container, false);
        awesomeValidation = new AwesomeValidation (ValidationStyle.BASIC);
        add_new_next_details = (Button) rootView.findViewById (R.id.add_new_next_details);
        save_store = (Button) rootView.findViewById (R.id.save_store);
        store_name = (EditText) rootView.findViewById (R.id.store_name);
        store_no = (EditText) rootView.findViewById (R.id.store_no);
        floor = (EditText) rootView.findViewById (R.id.floor);
        address = (EditText) rootView.findViewById (R.id.address);
        phone_no = (EditText) rootView.findViewById (R.id.phone_no);
        phone_no2 = (EditText) rootView.findViewById (R.id.phone_no2);

        save_store.setOnClickListener (this);
        add_new_next_details.setOnClickListener (this);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void swapFragment() {
        CameraFragment newCameraFragment = new CameraFragment ();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction ();
        fragmentTransaction.replace (R.id.frame_container, newCameraFragment);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ();
    }

    private void submitForm() {
        // Validate the form...
        if (awesomeValidation.validate ()) {

            add_new_next_details.setEnabled (true);
            add_new_next_details.setTextColor (Color.WHITE);
            add_new_next_details.setBackgroundColor (getResources ().getColor (R.color.colorPrimary));
            save_store.setVisibility (View.INVISIBLE);
            // Here, we are sure that form is successfully validated. So, do your stuffs now...
            Toast.makeText (this.getContext (), "Form Validated Successfully", Toast.LENGTH_SHORT).show ();
        }
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation (this.getActivity (), R.id.store_name, RegexTemplate.NOT_EMPTY, R.string.invalid_name_feild);
        awesomeValidation.addValidation (this.getActivity (), R.id.store_no, RegexTemplate.NOT_EMPTY, R.string.invalid_name_feild);
        awesomeValidation.addValidation (this.getActivity (), R.id.floor, RegexTemplate.NOT_EMPTY, R.string.invalid_name_feild);
        awesomeValidation.addValidation (this.getActivity (), R.id.address, RegexTemplate.NOT_EMPTY, R.string.invalid_name_feild);
        awesomeValidation.addValidation (this.getActivity (), R.id.phone_no, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.save_store:
                submitForm ();
                break;
            case R.id.add_new_next_details:
                swapFragment ();
                break;
        }

    }
}
