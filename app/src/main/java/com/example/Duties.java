package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fypproject.AdminPanel;
import com.example.fypproject.R;
import com.example.fypproject.Register_Work_Disk;

public class Duties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duties);
    }

    /*check update method*/
    public void viewDuties(View view) {
        Intent intent = new Intent(Duties.this, ViewDuties.class);
        startActivity(intent);
        finish();

    }

    public void AssignDuties(View view) {
        Intent intent = new Intent(Duties.this, com.example.fypproject.AssignDuties.class);
        startActivity(intent);
        finish();

    }
}
