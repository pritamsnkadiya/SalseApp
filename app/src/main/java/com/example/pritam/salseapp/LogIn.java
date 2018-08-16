package com.example.pritam.salseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private Button login_submit;
    private AwesomeValidation awesomeValidation;
    private EditText name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_log_in);
        awesomeValidation = new AwesomeValidation (ValidationStyle.BASIC);

        login_submit = (Button) findViewById (R.id.login_submit);
        name = (EditText) findViewById (R.id.name);
        phone = (EditText) findViewById (R.id.phone);
        login_submit.setOnClickListener (this);
        addValidationToViews ();
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation (this, R.id.name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation (this, R.id.phone, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
    }

    private void submitForm() {
        // Validate the form...
        if (awesomeValidation.validate ()) {
            // Here, we are sure that form is successfully validated. So, do your stuffs now...
            Toast.makeText (this, "Form Validated Successfully", Toast.LENGTH_SHORT).show ();
            Intent intent = new Intent (LogIn.this, OTPActivity.class);
            startActivity (intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.login_submit:
                submitForm ();
                break;
        }
    }
}
