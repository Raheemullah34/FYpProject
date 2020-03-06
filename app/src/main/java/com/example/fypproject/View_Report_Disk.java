package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ViewTeamReports;

public class View_Report_Disk extends AppCompatActivity {

    Button btn_Vteam_Report,btn_Vparent_Feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__report__disk);


        btn_Vteam_Report = findViewById(R.id.btn_Vteam_Report);
        btn_Vparent_Feedback = findViewById(R.id.btn_Vparent_Feedback);


        btn_Vteam_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_Report_Disk.this, ViewTeamReports.class);
                startActivity(intent);




            }
        });

        btn_Vparent_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(View_Report_Disk.this,Parent_Feedback_Disk.class);
                startActivity(intent);

            }
        });



    }
}
