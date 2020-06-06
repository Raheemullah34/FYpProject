package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Parent_Disk extends AppCompatActivity {

       Button btn_P_R_Child,btn_P_Complaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__disk);


        //check
        btn_P_R_Child = findViewById(R.id.btn_P_R_Child);
        btn_P_Complaint = findViewById(R.id.btn_P_Complaint);


        btn_P_R_Child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Parent_Disk.this,Register_Child_Disk.class);
                startActivity(intent);


            }
        });

        btn_P_Complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Parent_Disk.this,Send_Parent_FeedBack.class);
                startActivity(intent);



            }
        });



    }
}
