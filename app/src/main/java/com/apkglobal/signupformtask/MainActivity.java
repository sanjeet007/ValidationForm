package com.apkglobal.signupformtask;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText firstName,lastName,emailId,mobileNumber;
    private TextInputLayout firstName_layout,lastName_layout,emailId_layout,mobileNumber_layout;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName=findViewById(R.id.firstName);
        firstName_layout=findViewById(R.id.firstNameTIL);
        lastName=findViewById(R.id.lastName);
        lastName_layout=findViewById(R.id.lastNameTIL);
        emailId=findViewById(R.id.emailId);
        emailId_layout=findViewById(R.id.emailIdTIL);
        mobileNumber=findViewById(R.id.mobileno);
        mobileNumber_layout=findViewById(R.id.mobileNoTIL);
        btnRegister=findViewById(R.id.btn);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        firstName_layout.getEditText().addTextChangedListener(signUpTextWatcher);
        lastName_layout.getEditText().addTextChangedListener(signUpTextWatcher);
        emailId_layout.getEditText().addTextChangedListener(signUpTextWatcher);
        mobileNumber_layout.getEditText().addTextChangedListener(signUpTextWatcher);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateFirstName() | !validateLastName() | !validateEmail()|!validateMobileNo())
                {
                    return;
                }

                String data="First Name: "+firstName_layout.getEditText().getText().toString();
                data+="\n";
                data+="Last Name: "+lastName_layout.getEditText().getText().toString();
                data+="\n";
                data+="Email: "+emailId_layout.getEditText().getText().toString();
                data+="\n";
                data+="Mobile: "+mobileNumber_layout.getEditText().getText().toString();
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private boolean validateFirstName()
    {
        String fn=firstName_layout.getEditText().getText().toString().trim();

        if(fn.isEmpty())
        {
            firstName_layout.setError("Field Can't be blank");
            return false;

        }else if(fn.length()>20){
            firstName_layout.setError("First name too long");
            return false;
        }else if(fn.contains(" ")) {
            firstName_layout.setError("Spaces not allowed");
            return false;
        }else if(!fn.matches("[A-Za-z]+")) {
            firstName_layout.setError("Only Alphabets");
            return false;
        }else {
            firstName_layout.setError(null);
            //firstName_layout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateLastName()
    {
        String ln=lastName_layout.getEditText().getText().toString().trim();

        if(ln.isEmpty())
        {
            lastName_layout.setError("Field Can't be blank");
            return false;
        }else if(ln.length()>20) {
            lastName_layout.setError("Last name too long");
            return false;
        }else if(ln.contains(" ")) {
            lastName_layout.setError("Spaces not allowed");
            return false;
        }else if(!ln.matches("[A-Za-z]+")) {
            lastName_layout.setError("Only Alphabets");
            return false;
        }else {
            lastName_layout.setError(null);
            //firstName_layout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail()
    {
        String emailPattern = "[a-zA-Z0-9._-]+@(gmail|yahoo|outlook|zoho|icloud|aol|hotmail|msn)+\\.+(com|org|net|gov|biz|info|jobs|co|in|eu|uk|it|de|jp|es|br|)";
        String email=emailId_layout.getEditText().getText().toString().trim();

        if(email.isEmpty())
        {
            emailId_layout.setError("Field Can't be blank");
            return false;
        }else if(!email.matches(emailPattern)){
            emailId_layout.setError("Please enter valid email");
            return false;
        }else {
            emailId_layout.setError(null);
            //firstName_layout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateMobileNo()
    {
        String mno=mobileNumber_layout.getEditText().getText().toString().trim();

        if(mno.isEmpty())
        {
            mobileNumber_layout.setError("Field Can't be blank");
            return false;
        }else if(!isValid(mno)){
            mobileNumber_layout.setError("Enter valid mobile number");
            return false;
        }else if(mno.length()!=10){
            mobileNumber_layout.setError("Enter valid mobile number");
            return false;
        }else {
            mobileNumber_layout.setError(null);
            //firstName_layout.setErrorEnabled(false);
            return true;
        }
    }


    private boolean isValid(String s)
    {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    private TextWatcher signUpTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String f=firstName_layout.getEditText().getText().toString();
            String l=lastName_layout.getEditText().getText().toString();
            String e=emailId_layout.getEditText().getText().toString();
            String m=mobileNumber_layout.getEditText().getText().toString();

            btnRegister.setEnabled(!f.isEmpty()&&!l.isEmpty()&&!e.isEmpty()&&!m.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
