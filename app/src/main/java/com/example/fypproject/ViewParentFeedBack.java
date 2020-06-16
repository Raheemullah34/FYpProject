package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewParentFeedBack extends AppCompatActivity {

    TextView tv_fathername,tv_date,tv_familyNo,tv_des;
    Button viewlocation;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parent_feed_back);
        tv_fathername=findViewById(R.id.tv_fathername);
        tv_date=findViewById(R.id.tv_date);
        tv_familyNo=findViewById(R.id.tv_familyNo);
        tv_des=findViewById(R.id.tv_des);
        viewlocation=findViewById(R.id.view_location);

        tv_fathername.setText(getIntent().getStringExtra("fathername").toString());
        tv_date.setText(getIntent().getStringExtra("date").toString());
        tv_familyNo.setText(getIntent().getStringExtra("familyno").toString());
        tv_des.setText(getIntent().getStringExtra("des").toString());
        address=getIntent().getStringExtra("address");

        viewlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewParentFeedBack.this,MapsActivity.class)
                        .putExtra("address",address));
            }
        });
    }
}
