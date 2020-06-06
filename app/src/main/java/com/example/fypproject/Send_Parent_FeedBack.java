package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.Parents_Model;
import com.example.fypproject.Services.VolleyService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Send_Parent_FeedBack extends AppCompatActivity {

    EditText tv_fathername,tv_date,tv_familyNo,tv_des,tv_cnic,tv_address;
    Button sendParentReport;
    VolleyService volleyService;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__parent__feed_back);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        tv_fathername=findViewById(R.id.tv_fathername);
        tv_date=findViewById(R.id.tv_date);
        tv_familyNo=findViewById(R.id.tv_familyNo);
        tv_des=findViewById(R.id.tv_des);
        tv_cnic=findViewById(R.id.tv_cnic);
        tv_address=findViewById(R.id.tv_address);
        sendParentReport=findViewById(R.id.sendParentReport);
        tv_date.setText(date);
        mProgressDialog=new ProgressDialog(this);

        volleyService=new VolleyService(this);

        sendParentReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_fathername.getText().toString().isEmpty()
                        && tv_familyNo.getText().toString().isEmpty()
                        && tv_des.getText().toString().isEmpty()
                        && tv_address.getText().toString().isEmpty()
                        && tv_cnic.getText().toString().isEmpty())
                {
                    Toast.makeText(Send_Parent_FeedBack.this, "Please Complete the form", Toast.LENGTH_SHORT).show();
                }
                else {

                    InternatStatus checkInternatStatusObj = new InternatStatus(Send_Parent_FeedBack.this);
                    Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(Send_Parent_FeedBack.this);
                    if (checkInternatStatus) {
                        mProgressDialog.setMessage("Please Wait........");
                        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.show();
                    Parents_Model parents_model=new Parents_Model();
                    parents_model.setFeedback_father_name(tv_fathername.getText().toString());
                    parents_model.setFeedback_date(tv_date.getText().toString());
                    parents_model.setFeedbac_family_number(tv_familyNo.getText().toString());
                    parents_model.setFeedback_decrition(tv_des.getText().toString());
                    parents_model.setFeedbac_cnic(tv_cnic.getText().toString());
                    parents_model.setFeedback_address(tv_address.getText().toString());

                    volleyService.sendParentFeedback(AppConstants.domain_url + AppConstants.directory + AppConstants.sendParentFeedback
                            , parents_model, new VolleyService.VolleyResponseListener() {
                                @Override
                                public void onSuccess(String response) {
                                    mProgressDialog.dismiss();
                                    Log.d(AppConstants.TAG," send parend feed back "+response);

                                }

                                @Override
                                public void onError(VolleyError error) {

                                    mProgressDialog.dismiss();
                                    Log.d(AppConstants.TAG,"error send parend feed back "+error.getMessage());
                                }

                            });
                }
                }
            }
        });
    }
}
