package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Adapters.Parents_Adapter;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.Model.Parents_Model;
import com.example.fypproject.Services.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Parent_Feedback_Disk extends AppCompatActivity {


    private RecyclerView recyclerView;
    Parents_Adapter duties_adapter;

    EditText start_date,end_date;
    String daysAfter;

    VolleyService volleyService;
    private ProgressDialog mProgressDialog;
    private ArrayList<com.example.fypproject.Model.Parents_Model> dutiesModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__feedback__disk);

        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);
        dutiesModelArrayList=new ArrayList<>();

        start_date=findViewById(R.id.start_date);
        end_date=findViewById(R.id.end_date);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        daysAfter=getCalculatedDate("yyyy-MM-dd",10);


        start_date.setText(date);
        end_date.setText(daysAfter);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fetch_team_repots();
    }

    public void search_between_dates(View view) {
        fetch_team_repots();
    }
    public void fetch_team_repots(){
        dutiesModelArrayList.removeAll(dutiesModelArrayList);

        InternatStatus checkInternatStatusObj=new InternatStatus(this);
        Boolean checkInternatStatus=checkInternatStatusObj.isNetworkConnected(this);



        if (checkInternatStatus) {
            mProgressDialog.setMessage("Please Wait........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            DutiesModel dateDutiesModel=new DutiesModel();
            dateDutiesModel.setDuty_star(start_date.getText().toString().trim());
            dateDutiesModel.setDuty_end(end_date.getText().toString().trim());

            volleyService.fetchDuties(AppConstants.domain_url + AppConstants.directory + AppConstants.fetch_parents_report,
                    dateDutiesModel, new VolleyService.VolleyResponseListener() {
                        @Override
                        public void onSuccess(String response) {

                            Log.d(AppConstants.TAG," : success parents feedback "+response);
                            if (response.matches("false")) {
                                mProgressDialog.dismiss();
                                Toast.makeText(Parent_Feedback_Disk.this, "No data Found", Toast.LENGTH_SHORT).show();


                            }
                            else {

                                mProgressDialog.dismiss();
                                try {
                                    JSONArray data = new JSONArray(response);
                                    for (int i = 0; i < data.length(); i++) {
                                        JSONObject jsonObject = (JSONObject) data.get(i);
                                        com.example.fypproject.Model.Parents_Model parents_feedback=new com.example.fypproject.Model.Parents_Model();
                                        parents_feedback.setFeedback_father_name(jsonObject.getString("fathername"));
                                        parents_feedback.setFeedbac_cnic(jsonObject.getString("cnic"));
                                        parents_feedback.setFeedback_address(jsonObject.getString("address"));
                                        parents_feedback.setFeedbac_family_number(jsonObject.getString("family_number"));
                                        parents_feedback.setFeedback_decrition(jsonObject.getString("description"));
                                        parents_feedback.setFeedbac_status(jsonObject.getString("status"));
                                        parents_feedback.setFeedback_date(jsonObject.getString("date"));

                                        dutiesModelArrayList.add(parents_feedback);



                                    }
                                    duties_adapter = new Parents_Adapter(dutiesModelArrayList,Parent_Feedback_Disk.this);

                                    Log.d(AppConstants.TAG,"success data size "+dutiesModelArrayList.size());
                                    try {
                                        recyclerView.setAdapter(duties_adapter);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d(AppConstants.TAG,": excaption adapter filled");
                                    }

                                }
                                catch (JSONException e){

                                    Log.d(AppConstants.TAG,": Json Exception Bring Duties "+e.getMessage());

                                }

                            }

                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });

        }
        /*;*/



    }




    public static String getCalculatedDate(String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);


        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
