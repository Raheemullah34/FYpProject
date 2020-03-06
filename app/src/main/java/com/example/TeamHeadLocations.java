package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Adapters.Duties_adapter;
import com.example.fypproject.Adapters.LocationAdapter;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.R;
import com.example.fypproject.Services.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamHeadLocations extends AppCompatActivity {

    private VolleyService volleyService;
    private ProgressDialog mProgressDialog;

    ArrayList<DutiesModel> dutiesLocationModel;
    private RecyclerView recyclerView;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_head_locations);

        getSupportActionBar().hide();
        volleyService = new VolleyService(this);
        mProgressDialog = new ProgressDialog(this);
        dutiesLocationModel = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchWorkerLocations();

    }


    public void fetchWorkerLocations() {


        InternatStatus checkInternatStatusObj = new InternatStatus(this);
        Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(this);


        if (checkInternatStatus) {
            mProgressDialog.setMessage("Please Wait........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            DutiesModel dateDutiesModel = new DutiesModel();


            volleyService.fetchHeadLocations(AppConstants.domain_url + AppConstants.directory + AppConstants.getHeadsLocations, new VolleyService.VolleyResponseListener() {
                @Override
                public void onSuccess(String response) {

                    Log.d(AppConstants.TAG, " : success Fetch Address " + response);
                    if (response.matches("false")) {
                        mProgressDialog.dismiss();
                        Toast.makeText(TeamHeadLocations.this, "No data Found", Toast.LENGTH_SHORT).show();


                    } else {

                        mProgressDialog.dismiss();
                        try {
                            JSONArray data = new JSONArray(response);
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = (JSONObject) data.get(i);
                                DutiesModel dutiesModel = new DutiesModel();
                                dutiesModel.setDuty_team_Name(jsonObject.getString("worker_name"));
                                dutiesModel.setDuty_address(jsonObject.getString("worker_address"));

                                dutiesLocationModel.add(dutiesModel);


                            }
                            locationAdapter = new LocationAdapter(dutiesLocationModel, TeamHeadLocations.this);

                            Log.d(AppConstants.TAG, "success data size " + dutiesLocationModel.size());
                            try {
                                recyclerView.setAdapter(locationAdapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d(AppConstants.TAG, ": excaption adapter filled");
                            }

                        } catch (JSONException e) {

                            Log.d(AppConstants.TAG, ": Json Exception Bring Address " + e.getMessage());

                        }

                    }

                }

                @Override
                public void onError(VolleyError error) {

                }
            });

        }
    }
}

