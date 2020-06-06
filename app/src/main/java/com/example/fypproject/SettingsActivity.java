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
import com.example.fypproject.Model.Worker;
import com.example.fypproject.Services.VolleyService;
import com.example.fypproject.SharePreferences.MySharePreferences;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsActivity extends AppCompatActivity {

    TextView worker_name,worker_cnic,worker_mobile;
    EditText password;
    Button changePassword;

    VolleyService volleyService;
    private ProgressDialog mProgressDialog;

    MySharePreferences mySharePreferences;
    Worker worker;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        worker_name=findViewById(R.id.worker_name);
        worker_cnic=findViewById(R.id.worker_cnic);
        worker_mobile=findViewById(R.id.worker_mobile);
        password=findViewById(R.id.password);
        changePassword=findViewById(R.id.changePassword);

        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);

        mySharePreferences=new MySharePreferences();
        worker=new Worker();


        worker=mySharePreferences.getUserData(this);
        Log.d(AppConstants.TAG, " "+worker.getWorkerCnic());
        bringData();
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });


    }

    private void bringData() {

        InternatStatus checkInternatStatusObj = new InternatStatus(SettingsActivity.this);
        Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(SettingsActivity.this);

        if (checkInternatStatus) {
            mProgressDialog.setMessage("Please Wait........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            volleyService.bringHeadData(AppConstants.domain_url + AppConstants.directory + AppConstants.bringHeadData
                    , worker.getWorkerCnic(), new VolleyService.VolleyResponseListener() {
                        @Override
                        public void onSuccess(String response) {
                            mProgressDialog.dismiss();
                            Log.d(AppConstants.TAG,"  onSuccess bringHeadData "+response);

                            try {
                                JSONArray data = new JSONArray(response);
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = (JSONObject) data.get(i);
                                    DutiesModel dutiesModel=new DutiesModel();
                                    userid=jsonObject.getString("id");
                                   worker_name.setText(jsonObject.getString("worker_name"));
                                   worker_cnic.setText(jsonObject.getString("worker_cnic"));
                                   worker_mobile.setText(jsonObject.getString("worker_contact"));
                                   password.setText(jsonObject.getString("password"));



                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {

                            mProgressDialog.dismiss();
                            Log.d(AppConstants.TAG,"  onError bringHeadData "+error.getMessage());
                        }
                    });
        }

    }

    public void changePassword(){

        InternatStatus checkInternatStatusObj = new InternatStatus(SettingsActivity.this);
        Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(SettingsActivity.this);

        if (checkInternatStatus) {
            mProgressDialog.setMessage("Please Wait........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            volleyService.changePassword(AppConstants.domain_url + AppConstants.directory + AppConstants.changepassword
                    , worker.getWorkerCnic(), password.getText().toString(), new VolleyService.VolleyResponseListener() {
                        @Override
                        public void onSuccess(String response) {
                            mProgressDialog.dismiss();
                            Log.d(AppConstants.TAG,"  onSuccess changePassword "+response);

                        }

                        @Override
                        public void onError(VolleyError error) {

                            mProgressDialog.dismiss();
                            Log.d(AppConstants.TAG,"  onError changePassword "+error.getMessage());

                        }
                    });
        }

    }
}
