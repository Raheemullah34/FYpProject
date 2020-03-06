package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.Worker;
import com.example.fypproject.Services.VolleyService;

public class Register_Work_Disk extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edTname,edFatherName,edTCnic,edTContact;
    Button btn_R_W_D_Register;


    String[] team_selection;
    String[] pos_selection;

    Spinner spinnerPos;
    Spinner spinnerTeam;

    private ArrayAdapter<String> arrayAdapter_pos;
    ArrayAdapter<String> arrayAdapter;

    private ProgressDialog mProgressDialog;
    VolleyService volleyService;
    Worker workerobj;

    private String loginStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_work_disk);

        mProgressDialog=new ProgressDialog(this);
        volleyService=new VolleyService(this);

        edTname = findViewById(R.id.edTname);
        edFatherName = findViewById(R.id.edTFathername);
        edTCnic = findViewById(R.id.edTcnic);
        edTContact = findViewById(R.id.edTcontact);
        btn_R_W_D_Register = findViewById(R.id.btn_R_W_D_Register);
        spinnerTeam = findViewById(R.id.spnTid);
        spinnerPos = findViewById(R.id.type);


        team_selection=getResources().getStringArray(R.array.TEAM);
        pos_selection=getResources().getStringArray(R.array.TEAM_Pos);




        arrayAdapter=new ArrayAdapter<>(Register_Work_Disk.this,android.R.layout.simple_list_item_1,team_selection);
        arrayAdapter_pos=new ArrayAdapter<>(Register_Work_Disk.this,android.R.layout.simple_list_item_1,pos_selection);
        spinnerTeam.setAdapter(arrayAdapter);
        spinnerPos.setAdapter(arrayAdapter_pos);


//        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.TeamID,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerTeam.setAdapter(adapter);
        spinnerTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                if (position==1){
                    loginStatus="1";
                }
                else {
                    loginStatus="0";


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn_R_W_D_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerWorkerData();


                
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void registerWorkerData(){
        if (edTname.getText().toString().length()<3){
            Toast.makeText(this, "Enter Worker Name", Toast.LENGTH_SHORT).show();
        }
        else if (edFatherName.getText().toString().length()<3){
            Toast.makeText(this, "Enter Worker Father Name", Toast.LENGTH_SHORT).show();

        }

        else if (edTCnic.getText().toString().length()<3){
            Toast.makeText(this, "Enter Worker Cnic", Toast.LENGTH_SHORT).show();

        }
        else if (edTContact.getText().toString().length()<3){
            Toast.makeText(this, "Enter Worker Contact Number", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Spinner View"+spinnerTeam.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();

            workerobj=new Worker();
            workerobj.setWorkerName(edTname.getText().toString().trim());
            workerobj.setWorkerFatherName(edFatherName.getText().toString().trim());
            workerobj.setWorkerCnic(edTCnic.getText().toString().trim());
            workerobj.setWorkerContact(edTContact.getText().toString().trim());
            workerobj.setLoginStatus(loginStatus);
            workerobj.setWorkerTeam(String.valueOf(spinnerTeam.getItemAtPosition(spinnerTeam.getSelectedItemPosition())));
            workerobj.setWorkerJobStatus(String.valueOf(spinnerPos.getItemAtPosition(spinnerPos.getSelectedItemPosition())));


            InternatStatus checkInternatStatusObj=new InternatStatus(this);
            Boolean checkInternatStatus=checkInternatStatusObj.isNetworkConnected(this);



            if (checkInternatStatus){
                mProgressDialog.setMessage("Please Wait........");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                volleyService.teamMateRegistration(AppConstants.domain_url + AppConstants.directory
                        + AppConstants.registerWorker_ur, workerobj, new VolleyService.VolleyResponseListener() {
                    @Override
                    public void onSuccess(String response) {

                        mProgressDialog.dismiss();
                        Log.d(AppConstants.TAG+"Succes Register Worker",response);
                        if (response.equals("exists")){

                            Toast.makeText(Register_Work_Disk.this, "Already Registerd", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.equalsIgnoreCase("done")){
                            Toast.makeText(Register_Work_Disk.this, "Registerd Successfull. . .", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(Register_Work_Disk.this, "Something went Wrong Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(VolleyError error) {
                        mProgressDialog.dismiss();
                        Log.e(AppConstants.TAG+"Volley Error  Worker",""+error.getMessage());
                    }


                });



        }

    }
}}
