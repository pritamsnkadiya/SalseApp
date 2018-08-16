package com.example.pritam.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.pritam.api.ApiClient;
import com.example.pritam.model.OtpReceived;
import com.example.pritam.salseapp.R;
import com.example.pritam.utils.Constant;
import com.example.pritam.utils.Methods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button submit_otp;
    private View rootView;
    private EditText otpReceive2;
    private AwesomeValidation awesomeValidation;
    String accessToken = "";
    private static String TAG = OTPFragment.class.getSimpleName ();
    OtpReceived otpReceived;

    public OTPFragment() {
        // Required empty public constructor
    }

    public void onDetach() {
        super.onDetach ();
    }

    public static OTPFragment newInstance(String param1, String param2) {
        OTPFragment fragment = new OTPFragment ();
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
        rootView = inflater.inflate (R.layout.fragment_ot, container, false);
        awesomeValidation = new AwesomeValidation (ValidationStyle.BASIC);

        submit_otp = (Button) rootView.findViewById (R.id.submit_otp);
        otpReceive2 = (EditText) rootView.findViewById (R.id.otpReceive2);
        Spinner spinner = (Spinner) rootView.findViewById (R.id.phone_spinner);
        submit_otp.setOnClickListener (this);
        spinner.setOnItemSelectedListener (this);
        List<String> categories = new ArrayList<String> ();
        categories.add ("Select Phone for OTP");
        categories.add ("9138104484");
        categories.add ("8959782829");
        categories.add ("9977300264");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String> (this.getActivity (), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter (dataAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation (this.getActivity (), R.id.otpReceive2, RegexTemplate.NOT_EMPTY, R.string.invalid_otp);
    }

    private void submitForm() {
        // Validate the form...
        if (awesomeValidation.validate ()) {
            // Here, we are sure that form is successfully validated. So, do your stuffs now...
            swapFragment ();
            Toast.makeText (this.getContext (), "OTP Validated Successfully", Toast.LENGTH_SHORT).show ();
        }
    }

    private void swapFragment() {
        Home newHome = new Home ();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction ();
        fragmentTransaction.replace (R.id.frame_container, newHome);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.submit_otp:
                submitForm ();
                break;
        }
    }

    private void callApiGetOTP(String token) {
        try {
            ApiClient.getSingletonApiClient ().otpCall (token, new Callback<OtpReceived> () {
                @Override
                public void onResponse(Call<OtpReceived> call, Response<OtpReceived> response) {

                    Log.d (TAG, "OtpReceived Data : " + response.body ());
                    otpReceived = response.body ();
                    if (otpReceived != null) {
                        // Store here
                        otpReceive2.setText (otpReceived.getOtpRecived ().get (0).getOtp ().toString ());
                        Log.d (TAG, otpReceived.getOtpRecived ().get (0).getOtp ().toString () + "");
                    } else {
                        Methods.showPromptMessage (Constant.ORDER_DETAIL);
                        getActivity ().finish ();
                    }
                }

                @Override
                public void onFailure(Call<OtpReceived> call, Throwable t) {
                    Log.d (TAG, "Fetching Data Error : " + t.getMessage ());
                }
            });
        } catch (Exception e) {
            Log.d (TAG, "Error msg : " + e.getMessage ());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition (position).toString ();
        if (position == 1) {
            callApiGetOTP (accessToken);
        }
        if (position == 3) {
            callApiGetOTP (accessToken);
        }
        // Showing selected spinner item
        // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}