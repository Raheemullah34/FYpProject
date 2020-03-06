package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.Users;
import com.example.fypproject.Model.Worker;
import com.example.fypproject.Services.VolleyService;
import com.example.fypproject.SharePreferences.MySharePreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText et_email,et_password;

    private ProgressDialog mProgressDialog;
    VolleyService volleyService;

    MySharePreferences mySharePreferences;
    Boolean loginStatus;
    Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);

        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);
        mySharePreferences=new MySharePreferences(this);
        loginStatus=mySharePreferences.getLoginStatus(this);
        worker=new Worker();
        worker=mySharePreferences.getUserData(this);

//        if (loginStatus){
//            worker=mySharePreferences.getUserData(this);
//
//            if (worker.getWorkerJobStatus().matches("admin")){
//                startActivity(new Intent(this,AdminPanel.class));
//                finish();
//
//            }
//            else if (worker.getWorkerJobStatus().matches("Head")){
//                startActivity(new Intent(this,Worker_Disk.class));
//                finish();
//
//
//            }
//        }






    }

    public void loginFun(View view) {

        InternatStatus checkInternatStatusObj = new InternatStatus(this);
        Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(this);



        if (checkInternatStatus) {
            mProgressDialog.setMessage("Please Wait........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

           Worker loginWorker=new Worker();
           loginWorker.setWorkerName(et_email.getText().toString().trim());
           loginWorker.setWorkerPassword(et_password.getText().toString().trim());

           volleyService.teamLoginActivity(AppConstants.domain_url + AppConstants.directory + AppConstants.loginUrl,
                   loginWorker, new VolleyService.VolleyResponseListener() {
                       @Override
                       public void onSuccess(String response) {

                           if (response.length()<25){
                               Toast.makeText(LoginActivity.this, "Password Not Matched", Toast.LENGTH_SHORT).show();

                               mProgressDialog.dismiss();
                           }
                           else {
                               Log.d(AppConstants.TAG, ": login Function" + response);
                               try {
                                   JSONObject jsonObject = new JSONObject(response);
                                   if (jsonObject.getBoolean("Success")) {

                                       Worker saveWorker = new Worker();
                                       saveWorker.setWorkerName(jsonObject.getString("worker_name"));
                                       saveWorker.setWorkerContact(jsonObject.getString("worker_contact"));
                                       saveWorker.setWorkerCnic(jsonObject.getString("worker_cnic"));
                                       saveWorker.setWorkerJobStatus(jsonObject.getString("worker_job_status"));
                                       saveWorker.setWorkerTeam(jsonObject.getString("worker_team"));
                                       Toast.makeText(LoginActivity.this, "check" + saveWorker.getWorkerJobStatus().matches("Head"), Toast.LENGTH_SHORT).show();

                                       MySharePreferences mySharePreferences=new MySharePreferences(LoginActivity.this);
                                       mySharePreferences.SaveUserData(LoginActivity.this,saveWorker);
                                       mySharePreferences.saveLoginStatus(LoginActivity.this,true);

                                       if (saveWorker.getWorkerJobStatus().matches("Head")) {
                                           startActivity(new Intent(LoginActivity.this,Worker_Disk.class));
                                           mySharePreferences.saveheadlogin(LoginActivity.this,true);
                                           finish();
                                       } else if (saveWorker.getWorkerJobStatus().matches("admin")) {
                                           startActivity(new Intent(LoginActivity.this,AdminPanel.class));
                                           mySharePreferences.saveLoginStatus(LoginActivity.this,true);
                                           finish();

                                       }
                                   } else {
                                       Toast.makeText(LoginActivity.this, "Does not login", Toast.LENGTH_SHORT).show();
                                   }


                               } catch (JSONException e) {
                                   e.printStackTrace();
                                   Log.d(AppConstants.TAG, ": login Json error");
                               }

                               mProgressDialog.dismiss();
                           }

                       }

                       @Override
                       public void onError(VolleyError error) {
                           mProgressDialog.dismiss();

                       }
                   });




        }

    }


}
