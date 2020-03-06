package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Model.DutiesModel;

public class ViewDutiesDescriptions extends AppCompatActivity {
    DutiesModel dutiesModel=new DutiesModel();

    TextView data_teamName,data_date,data_status,data_des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_duties_descriptions);

        data_teamName=findViewById(R.id.data_teamName);
        data_date=findViewById(R.id.data_date);
        data_status=findViewById(R.id.data_status);
        data_des=findViewById(R.id.data_des);

        dutiesModel.setDuty_team_Name(getIntent().getStringExtra("team_name"));
        dutiesModel.setDuty_address(getIntent().getStringExtra("address"));
        dutiesModel.setStatus(getIntent().getStringExtra("duty_status"));
        dutiesModel.setDuty_des(getIntent().getStringExtra("duty_des"));
        dutiesModel.setDuty_date(getIntent().getStringExtra("duty_date"));


        data_teamName.setText(dutiesModel.getDuty_team_Name());
        data_date.setText(dutiesModel.getDuty_date());
        data_status.setText(dutiesModel.getDuty_address());
        data_des.setText(dutiesModel.getDuty_des());



        Log.d(AppConstants.TAG," Team Name"+" "+dutiesModel.getDuty_team_Name()+
                " Team Duty Address "+" "+dutiesModel.getDuty_address()+
                " Team Task Status :"+dutiesModel.getStatus()+
                " Team Task Des :"+dutiesModel.getDuty_des());
    }
}
