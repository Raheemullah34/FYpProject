package com.example.fypproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.Services.VolleyService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssignDuties extends AppCompatActivity {

    private static final String TAG = "Assign Duties";
    private int year, month, day;

    String[] team_selection;
    String[] city_selection;

    Spinner spinner_city;
    Spinner spinnerTeam;

    TextView date_lable;

    private ArrayAdapter<String> arrayAdapter_pos;
    ArrayAdapter<String> arrayAdapter;

    private ProgressDialog mProgressDialog;
    VolleyService volleyService;

    EditText ed_assign_duties_address;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_duties);

        mProgressDialog=new ProgressDialog(this);
        volleyService=new VolleyService(this);

        ed_assign_duties_address=findViewById(R.id.ed_assign_duties_address);
        spinnerTeam=findViewById(R.id.spnTid);
        spinner_city =findViewById(R.id.sp_city);
       date_lable =findViewById(R.id.date_lable);

        team_selection=getResources().getStringArray(R.array.TEAM);
        city_selection =getResources().getStringArray(R.array.Cities);

        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,team_selection);
        arrayAdapter_pos=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, city_selection);
        spinnerTeam.setAdapter(arrayAdapter);
        spinner_city.setAdapter(arrayAdapter_pos);


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        date_lable.setText(date);

    }

    public void select_Date(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        date_lable.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @SuppressLint("LongLogTag")
    public void SubmitDuties(View view) {
        if (spinnerTeam.getSelectedItemId()==0 || spinner_city.getSelectedItemId()==0){
            Toast.makeText(this, "Team Or City Not Selected", Toast.LENGTH_SHORT).show();
        }
        else {

            InternatStatus checkInternatStatusObj = new InternatStatus(this);
            Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(this);


            if (checkInternatStatus) {
                mProgressDialog.setMessage("Please Wait........");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                DutiesModel dutiesModel = new DutiesModel();
                dutiesModel.setDuty_team_Name("" + spinnerTeam.getItemAtPosition(spinnerTeam.getSelectedItemPosition()));
                dutiesModel.setDuty_city("" + spinner_city.getItemAtPosition(spinner_city.getSelectedItemPosition()));
                dutiesModel.setDuty_address(ed_assign_duties_address.getText().toString() + " " + spinner_city.getItemAtPosition(spinner_city.getSelectedItemPosition()));
                dutiesModel.setDuty_date(date_lable.getText().toString());


                volleyService.assignDutties(AppConstants.domain_url + AppConstants.directory + AppConstants.addDuty, dutiesModel, new VolleyService.VolleyResponseListener() {
                            @Override
                            public void onSuccess(String response) {


                                mProgressDialog.dismiss();
                                Log.d(AppConstants.TAG + " Assign Duties  : submit duty responce ", response);

                                if (response.equals("exists")){

                                    Toast.makeText(AssignDuties.this, "Duty Already Assigned ", Toast.LENGTH_SHORT).show();
                                }
                                else if (response.equalsIgnoreCase("done")){
                                    Toast.makeText(AssignDuties.this, "Duty Assigned Successfully. . .", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(AssignDuties.this, "Something went Wrong Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(VolleyError error) {

                                mProgressDialog.dismiss();
                                Log.e(AppConstants.TAG+"Volley Error  Assign Duties",""+error.getMessage());

                            }
                        }
                );
            }
        }

        Log.d(TAG+":"+"submit clicked"," Team Selected "+spinnerTeam.getItemAtPosition(spinnerTeam.getSelectedItemPosition())
        +" "+" City Selected "+spinner_city.getItemAtPosition(spinner_city.getSelectedItemPosition())
        +" "+ " Address "+ed_assign_duties_address.getText().toString()+" "+" Date "+ date_lable.getText().toString());

    }

}