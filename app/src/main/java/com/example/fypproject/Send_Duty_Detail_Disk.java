package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.Services.VolleyService;

public class Send_Duty_Detail_Disk extends AppCompatActivity {

    EditText ed_S_DutyDetail;
    Button btn_S_DutyDetail,viewlocation;

    private ProgressDialog mProgressDialog;
    VolleyService volleyService;
    TextView tv_location;
    String idhere;
    RadioGroup radio_group_status;
    private String duty_status;
    private String checStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__duty__detail__disk);
        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);


        ed_S_DutyDetail = findViewById(R.id.ed_S_DutyDetail);
        btn_S_DutyDetail = findViewById(R.id.viewDuties);
        tv_location = findViewById(R.id.tv_location);
        radio_group_status = findViewById(R.id.radio_group_status);
        viewlocation = findViewById(R.id.viewlocation);
        tv_location.setText(getIntent().getStringExtra("address"));
        idhere=getIntent().getStringExtra("id").toString();
        checStatus=getIntent().getStringExtra("duty_status").toString();
        if (checStatus.matches("1")){
            radio_group_status.check(R.id.done_radio);

            Toast.makeText(this, "here"+checStatus, Toast.LENGTH_SHORT).show();
        }
        else {
            radio_group_status.check(R.id.pending);
            Toast.makeText(this, "here"+checStatus, Toast.LENGTH_SHORT).show();
        }

        radio_group_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.done_radio:
                        duty_status="1";
                        break;
                    case R.id.pending:

                        duty_status="0";
                        break;


                }
            }

        });


        viewlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Send_Duty_Detail_Disk.this,MapsActivity.class)
                .putExtra("address",tv_location.getText().toString()));
            }
        });


        btn_S_DutyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InternatStatus checkInternatStatusObj = new InternatStatus(Send_Duty_Detail_Disk.this);
                Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(Send_Duty_Detail_Disk.this);



                if (checkInternatStatus) {
                    mProgressDialog.setMessage("Please Wait........");
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();
                    DutiesModel dutiesModel=new DutiesModel();
                    dutiesModel.setId(idhere);
                    dutiesModel.setStatus(duty_status);
                    dutiesModel.setDuty_des(ed_S_DutyDetail.getText().toString().trim());
                    dutiesModel.setDuty_address(tv_location.getText().toString().trim());


                    volleyService.postReportByHead(AppConstants.domain_url + AppConstants.directory + AppConstants.post_by_head, dutiesModel
                     , new VolleyService.VolleyResponseListener() {
            @Override
            public void onSuccess(String response) {
                mProgressDialog.dismiss();
                if (response.matches("Record updated successfully")){
                    Toast.makeText(Send_Duty_Detail_Disk.this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                }
                Log.d(AppConstants.TAG,": send duty responce "+response);

            }

            @Override
            public void onError(VolleyError error) {

            }
        });


                }
                }






        });



    }
}
