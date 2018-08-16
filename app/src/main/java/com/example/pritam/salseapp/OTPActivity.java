package com.example.pritam.salseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.pritam.api.ApiClient;
import com.example.pritam.model.OtpReceived;
import com.example.pritam.utils.Constant;
import com.example.pritam.utils.Methods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    private Button otp_submit;
    private EditText otpReceive;
    OtpReceived otpReceived;
    String accessToken = "";
    private AwesomeValidation awesomeValidation;
    private static String TAG = OTPActivity.class.getSimpleName ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_otp);
        awesomeValidation = new AwesomeValidation (ValidationStyle.BASIC);

        otp_submit = (Button) findViewById (R.id.otp_submit);
        otpReceive = (EditText) findViewById (R.id.otpReceive);
        otp_submit.setOnClickListener (this);
        addValidationToViews ();
        callApiGetOTP (accessToken);
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation (this, R.id.otpReceive, RegexTemplate.NOT_EMPTY, R.string.invalid_otp);
    }

    private void submitForm() {
        // Validate the form...
        if (awesomeValidation.validate ()) {
            // Here, we are sure that form is successfully validated. So, do your stuffs now...
            Toast.makeText (this, "OTP Validated Successfully", Toast.LENGTH_SHORT).show ();
            Intent intent = new Intent (OTPActivity.this, MainActivity.class);
            startActivity (intent);
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
                        otpReceive.setText (otpReceived.getOtpRecived ().get (0).getOtp ().toString ());
                        Log.d ("OTP", otpReceived.getOtpRecived ().get (0).getOtp ().toString () + "");
                    } else {
                        Methods.showPromptMessage (Constant.ORDER_DETAIL);
                        finish ();
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
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.otp_submit:
                submitForm ();
                break;
        }
    }
}
