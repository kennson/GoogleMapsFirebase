package com.mobility.dictwilson.firebase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private DatabaseReference mDatabase;
    private Button buttonSave;
    private Button buttonProceed;
    private EditText editTextName;
    private EditText editTextLat;
    private EditText editTextLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonProceed = (Button)findViewById(R.id.buttonProceed);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextLat = (EditText)findViewById(R.id.editTextLat);
        editTextLng = (EditText)findViewById(R.id.editTextLng);
        buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        double latitute = Double.parseDouble(editTextLat.getText().toString().trim());
        double longitude = Double.parseDouble(editTextLng.getText().toString().trim());
        UserInformation userInformation = new UserInformation(name,latitute,longitude);
        DatabaseReference newPost = mDatabase.push();
        newPost.setValue(userInformation);

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v){
        if(v == buttonProceed){
            finish();
        }
        if (v == buttonSave){
            saveUserInformation();
            editTextName.getText().clear();
            editTextLat.getText().clear();
            editTextLng.getText().clear();
        }
    }
}
