package com.example.passingdatabyintent;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtUser, txtAge;
    Button btnSign;
    Spinner gender;

    ArrayList<String> hobbies = new ArrayList<>();   //create an empty arr to put check box values from hobbies



    static final int RESULT_CODE = 299;  //result code when you request info from other act/y


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtUser = findViewById(R.id.textUsername);
        txtAge = findViewById(R.id.textAge);
        btnSign = findViewById(R.id.btnSignUp);

        gender = findViewById(R.id.spinnerGender);
        if (gender != null) {
            gender.setOnItemSelectedListener(this);
        }

        // Spinner Drop down elements
        List<String> genderList = new ArrayList<String>();
        genderList.add("Select your gender");
        genderList.add("Female");
        genderList.add("Male");

        // Creating adapter for spinner and use pre built simple_spinner_item layout for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender.setAdapter(dataAdapter);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtUser.getText().toString().isEmpty()
                        && txtAge.getText().toString().isEmpty()) {

                    Toast.makeText(SignUpActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();


                } else {

                        CheckBox checkBox; //iterate the loop in order to get values of check box
                        for (int i = 1; i <=3; i++) {
                            int id = getResources().getIdentifier("chkboxSgnup"+i, "id", getPackageName());
                            checkBox = findViewById(id);

                            if (checkBox.isChecked())
                            {
                                hobbies.add(checkBox.getText().toString()); //if check box is checked it get the text
                            }
                        }

                    Intent i = new Intent(); //get the date from user input
                    i.putExtra("USERNAME", txtUser.getText().toString());
                    i.putExtra("AGE", txtAge.getText().toString());
                    i.putExtra("GENDER",gender.getSelectedItem().toString());
                    i.putExtra("HOBBIES", hobbies);


                    setResult(RESULT_CODE, i);
                    finish();
                }
    }
});
        }

@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}



