package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewParentFeedBack extends AppCompatActivity {

    TextView tv_fathername,tv_date,tv_familyNo,tv_des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parent_feed_back);
        tv_fathername=findViewById(R.id.tv_fathername);
        tv_date=findViewById(R.id.tv_date);
        tv_familyNo=findViewById(R.id.tv_familyNo);
        tv_des=findViewById(R.id.tv_des);

        tv_fathername.setText(getIntent().getStringExtra("fathername").toString());
        tv_date.setText(getIntent().getStringExtra("date").toString());
        tv_familyNo.setText(getIntent().getStringExtra("familyno").toString());
        tv_des.setText(getIntent().getStringExtra("des").toString());
    }
}
