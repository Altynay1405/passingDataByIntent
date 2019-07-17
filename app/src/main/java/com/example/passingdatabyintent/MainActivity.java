package com.example.passingdatabyintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button getInfoButton;
    TextView txtViewName,txtViewAge,txtViewGender;
    ListView listViewhobbies;

   static final int REQUEST_CODE = 199;  //rqst code we use when we get back data from other act/y to main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInfoButton = findViewById(R.id.btnGetInfo);

        txtViewName = findViewById(R.id.textUser);
        txtViewAge = findViewById(R.id.textAge);
        txtViewGender = findViewById(R.id.textGender);
        listViewhobbies = findViewById(R.id.listViewHobbies);

        getInfoButton.setOnClickListener(new View.OnClickListener() {  //get info btn onClick handling and intent to go to sign up act/y
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivityForResult(i,REQUEST_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE && resultCode == SignUpActivity.RESULT_CODE){

            txtViewName.setText(data.getStringExtra("USERNAME"));  //get the data from sign up act/y and push it to txt view main ct/y
            txtViewAge.setText(data.getStringExtra("AGE"));    //
            txtViewGender.setText(data.getStringExtra("GENDER"));

            ArrayList<String> hobbies = (ArrayList<String>) data.getSerializableExtra("HOBBIES");

            //listview_hobbies - layout design file and txtViewHobbiesLayoutLabel it is id of this file   hobbies - array
            ArrayAdapter<String> hl = new ArrayAdapter(this, R.layout.listview_hobbies, R.id.txtViewHobbiesLayoutLabel, hobbies);
            listViewhobbies.setAdapter(hl);



        }
    }
}
