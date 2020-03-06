package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.Duties;
import com.example.Main_Menue;
import com.example.TeamHeadLocations;
import com.example.fypproject.SharePreferences.MySharePreferences;

public class AdminPanel extends AppCompatActivity  implements View.OnClickListener {

    LinearLayout btn_R_Worker,btn_A_Duty,btn_R_Child,btn_L_Team_Worker,btn_V_Reports,btn_logout;
    MySharePreferences sharePreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);


        btn_R_Worker = findViewById(R.id.btnworker);
        btn_A_Duty = findViewById(R.id.btnAduty);
        btn_R_Child = findViewById(R.id.btnRchild);
        btn_L_Team_Worker = findViewById(R.id.btnLteamworker);
        btn_V_Reports = findViewById(R.id.btnVreports);
        btn_logout=findViewById(R.id.btn_logout);

        sharePreferences = new MySharePreferences(this);



      //Registor

        btn_R_Worker.setOnClickListener(this);
        btn_A_Duty.setOnClickListener(this);
        btn_R_Child.setOnClickListener(this);
        btn_L_Team_Worker.setOnClickListener(this);
        btn_V_Reports.setOnClickListener(this);
        btn_logout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnworker:
               startActivity(new Intent(AdminPanel.this,Register_Work_Disk.class));
                break;
            case R.id.btnAduty:
               startActivity(new Intent(AdminPanel.this,Duties.class));
                break;
            case R.id.btnRchild:
                startActivity(new Intent(AdminPanel.this,Register_Child_Disk.class));
                break;
            case R.id.btnLteamworker:
                startActivity(new Intent(AdminPanel.this,TeamHeadLocations.class));
                break;
            case R.id.btnVreports:
                startActivity(new Intent(AdminPanel.this,View_Report_Disk.class));
                break;
            case R.id.btn_logout:

                sharePreferences.saveLoginStatus(AdminPanel.this,false);
                startActivity(new Intent(AdminPanel.this, Main_Menue.class));
                break;

        }
    }
}
