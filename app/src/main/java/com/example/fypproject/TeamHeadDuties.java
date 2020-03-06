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
import com.example.ViewDuties;
import com.example.fypproject.Adapters.Duties_adapter;
import com.example.fypproject.Adapters.Team_Duties;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.Model.Worker;
import com.example.fypproject.Services.VolleyService;
import com.example.fypproject.SharePreferences.MySharePreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TeamHeadDuties extends AppCompatActivity {

    private RecyclerView recyclerView;
    Team_Duties duties_adapter;

    EditText start_date,end_date;
    String daysAfter;

    VolleyService volleyService;
    private ProgressDialog mProgressDialog;
    private ArrayList<DutiesModel> dutiesModelArrayList;

    MySharePreferences mySharePreferences;
    Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_head_duties);
        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);
        mySharePreferences=new MySharePreferences(this);
        worker=new Worker();
        worker=mySharePreferences.getUserData(this);
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

        fetchDuties();
    }

    public void search_between_dates(View view) {
        fetchDuties();

    }

    public void fetchDuties(){
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
            dateDutiesModel.setDuty_team_Name(worker.getWorkerTeam());

            volleyService.fetchTeamDuties(AppConstants.domain_url + AppConstants.directory + AppConstants.fetch_teams_duties,
                    dateDutiesModel, new VolleyService.VolleyResponseListener() {
                        @Override
                        public void onSuccess(String response) {

                            Log.d(AppConstants.TAG," : success duties "+response);
                            if (response.matches("false")) {
                                mProgressDialog.dismiss();
                                Toast.makeText(TeamHeadDuties.this, "No data Found", Toast.LENGTH_SHORT).show();


                            }
                            else {

                                mProgressDialog.dismiss();
                                try {
                                    JSONArray data = new JSONArray(response);
                                    for (int i = 0; i < data.length(); i++) {
                                        JSONObject jsonObject = (JSONObject) data.get(i);
                                        DutiesModel dutiesModel=new DutiesModel();
                                        dutiesModel.setId(jsonObject.getString("id"));
                                        dutiesModel.setDuty_team_Name(jsonObject.getString("duty_team_name"));
                                        dutiesModel.setDuty_date(jsonObject.getString("duty_date"));
                                        dutiesModel.setDuty_address(jsonObject.getString("duty_address"));
                                        dutiesModel.setStatus(jsonObject.getString("duty_status"));

                                        dutiesModelArrayList.add(dutiesModel);



                                    }
                                    duties_adapter = new Team_Duties(dutiesModelArrayList,TeamHeadDuties.this);

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
