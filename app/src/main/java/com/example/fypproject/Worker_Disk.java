package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Main_Menue;
import com.example.fypproject.Model.Worker;
import com.example.fypproject.SharePreferences.MySharePreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class Worker_Disk extends AppCompatActivity {


    Button btn_W_R_Child,btn_W_S_DutyDetail,btn_logut,btn_W_S_Settings;
    MySharePreferences mySharePreferences;
    Worker worker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__disk);



        mySharePreferences=new MySharePreferences(this);
        worker=new Worker();

        worker=mySharePreferences.getUserData(this);

        FirebaseMessaging.getInstance().subscribeToTopic(worker.getWorkerTeam()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Success"+worker.getWorkerTeam(), Toast.LENGTH_LONG).show();
            }
        });


        btn_W_R_Child = findViewById(R.id.btn_W_R_Child);
        btn_W_S_DutyDetail = findViewById(R.id.btn_W_S_DutyDetail);
        btn_logut=findViewById(R.id.btnlogouthead);
        btn_W_S_Settings=findViewById(R.id.btn_W_S_Settings);


        btn_W_R_Child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Worker_Disk.this,Register_Child_Disk.class);
                startActivity(intent);

            }
        });


        btn_W_S_DutyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           Intent intent = new Intent(Worker_Disk.this,TeamHeadDuties.class);
           startActivity(intent);


            }
        });
        btn_logut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic(worker.getWorkerTeam()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Success"+worker.getWorkerTeam(), Toast.LENGTH_LONG).show();
                    }
                });
                mySharePreferences.saveheadlogin(Worker_Disk.this,false);
                startActivity(new Intent(Worker_Disk.this, Main_Menue.class));
            }
        });


        btn_W_S_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Worker_Disk.this,SettingsActivity.class);
                startActivity(intent);
            }
        });




    }
}
