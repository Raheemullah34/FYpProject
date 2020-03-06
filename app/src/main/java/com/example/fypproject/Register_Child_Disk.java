package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fypproject.Constants.AppConstants;
import com.example.fypproject.Helpers.InternatStatus;
import com.example.fypproject.Model.ChildModel;
import com.example.fypproject.Services.VolleyService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.LogRecord;

public class Register_Child_Disk extends AppCompatActivity {

    EditText ed_Rchild_name, ed_Rchild_F_name, ed_Rchild_F_number, ed_Rchild_Location,ed_Rchild_F_name_cnic,
            ed_Rchild_date;
    Button btn_Rchild_Register;
    private ProgressDialog mProgressDialog;
    VolleyService volleyService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__child__disk);
        volleyService=new VolleyService(this);
        mProgressDialog=new ProgressDialog(this);
        
        
        ed_Rchild_name = findViewById(R.id.ed_Rchild_name);
        ed_Rchild_F_name = findViewById(R.id.ed_Rchild_F_name);
        ed_Rchild_F_number = findViewById(R.id.ed_Rchild_F_number);
        ed_Rchild_Location = findViewById(R.id.ed_Rchild_Location);
        btn_Rchild_Register = findViewById(R.id.btn_Rchild_Register);
        ed_Rchild_F_name_cnic = findViewById(R.id.ed_Rchild_F_name_cnic);
        ed_Rchild_date = findViewById(R.id.ed_Rchild_date);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        ed_Rchild_date.setText(date);

        btn_Rchild_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ed_Rchild_name.getText().toString().length()<2 ||
                        ed_Rchild_F_name.getText().toString().length()<2 ||
                        ed_Rchild_F_number.getText().toString().length()<2 ||
                        ed_Rchild_Location.getText().toString().length()<2 ||
                        ed_Rchild_F_name_cnic.getText().toString().length()<2){
                    Toast.makeText(Register_Child_Disk.this, "Fill Complete Data", Toast.LENGTH_SHORT).show();

                }
                else {
                    registerChild();
                }


                
            }
        });
        
        
        
    }

    public void registerChild(){
        InternatStatus checkInternatStatusObj = new InternatStatus(this);
        Boolean checkInternatStatus = checkInternatStatusObj.isNetworkConnected(this);


        if (checkInternatStatus) {
            mProgressDialog.setMessage("Please Wait........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            ChildModel childModel=new ChildModel();
            childModel.setChild_name(ed_Rchild_name.getText().toString().trim());
            childModel.setChild_father_name(ed_Rchild_F_name.getText().toString().trim());
            childModel.setChild_family_no(ed_Rchild_F_number.getText().toString().trim());
            childModel.setFather_cnic(ed_Rchild_F_name_cnic.getText().toString().trim());
            childModel.setChild_location(ed_Rchild_Location.getText().toString().trim());
            childModel.setChild_reg_date(ed_Rchild_date.getText().toString().trim());
            childModel.setEntery_person_type("admin");

            volleyService.childRegistration(AppConstants.domain_url + AppConstants.directory + AppConstants.newChildEntery, childModel,
                    new VolleyService.VolleyResponseListener() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onSuccess(String response) {

                            mProgressDialog.dismiss();
                            Log.d(AppConstants.TAG + " Success child Register   : submit duty responce ", response);

                            if (response.equals("exists")) {
                                Toast.makeText(Register_Child_Disk.this, "Child Already Exists. . ", Toast.LENGTH_SHORT).show();


                            }
                            else {
                                Toast.makeText(Register_Child_Disk.this, "Child Registration Successfully", Toast.LENGTH_SHORT).show();

                                ed_Rchild_name.setText("");
                                ed_Rchild_F_name.setText("");
                                ed_Rchild_F_number.setText("");
                                ed_Rchild_Location.setText("");
                                ed_Rchild_F_name_cnic.setText("");


                            }

                            }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });


        }
    }
}
