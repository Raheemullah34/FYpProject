package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.Services.VolleyService;

public class Send_Duty_Detail_Disk extends AppCompatActivity {

    EditText ed_S_DutyDetail;
    Button btn_S_DutyDetail;

    private ProgressDialog mProgressDialog;
    VolleyService volleyService;
    TextView tv_location;
    String idhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__duty__detail__disk);
        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);


        ed_S_DutyDetail = findViewById(R.id.ed_S_DutyDetail);
        btn_S_DutyDetail = findViewById(R.id.viewDuties);
        tv_location = findViewById(R.id.tv_location);
        tv_location.setText(getIntent().getStringExtra("address"));
        idhere=getIntent().getStringExtra("id").toString();




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
                    dutiesModel.setStatus("25");
                    dutiesModel.setDuty_des(ed_S_DutyDetail.getText().toString().trim());


                    volleyService.postReportByHead(AppConstants.domain_url + AppConstants.directory + AppConstants.post_by_head, dutiesModel
                     , new VolleyService.VolleyResponseListener() {
            @Override
            public void onSuccess(String response) {
                mProgressDialog.dismiss();
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
