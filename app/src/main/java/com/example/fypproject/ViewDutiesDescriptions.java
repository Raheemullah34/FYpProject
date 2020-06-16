package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Model.DutiesModel;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class ViewDutiesDescriptions extends AppCompatActivity {
    DutiesModel dutiesModel=new DutiesModel();

    TextView data_teamName,data_date,data_status,data_des;
    Button view_location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_duties_descriptions);

        data_teamName=findViewById(R.id.data_teamName);
        data_date=findViewById(R.id.data_date);
        data_status=findViewById(R.id.data_status);
        data_des=findViewById(R.id.data_des);
        view_location=findViewById(R.id.view_location);

        dutiesModel.setDuty_team_Name(getIntent().getStringExtra("team_name"));
        dutiesModel.setDuty_address(getIntent().getStringExtra("address"));
        dutiesModel.setStatus(getIntent().getStringExtra("duty_status"));
        dutiesModel.setDuty_des(getIntent().getStringExtra("duty_des"));
        dutiesModel.setDuty_date(getIntent().getStringExtra("duty_date"));


        data_teamName.setText(dutiesModel.getDuty_team_Name());
        data_date.setText(dutiesModel.getDuty_date());
        data_status.setText(dutiesModel.getDuty_address());
        data_des.setText(dutiesModel.getDuty_des());



        view_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewDutiesDescriptions.this,MapsActivity.class)
                .putExtra("address",dutiesModel.getDuty_address()));
            }
        });

        Log.d(AppConstants.TAG," Team Name"+" "+dutiesModel.getDuty_team_Name()+
                " Team Duty Address "+" "+dutiesModel.getDuty_address()+
                " Team Task Status :"+dutiesModel.getStatus()+
                " Team Task Des :"+dutiesModel.getDuty_des());
    }


}