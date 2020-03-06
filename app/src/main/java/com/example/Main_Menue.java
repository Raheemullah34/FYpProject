package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fypproject.AdminPanel;
import com.example.fypproject.LoginActivity;
import com.example.fypproject.Parent_Disk;
import com.example.fypproject.R;
import com.example.fypproject.SharePreferences.MySharePreferences;
import com.example.fypproject.Worker_Disk;

public class Main_Menue extends AppCompatActivity implements View.OnClickListener {

Button btn_admin1,btn_head1,btn_faimily1;

  MySharePreferences sharePreferences;

  boolean head,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menue);
        btn_admin1=findViewById(R.id.btnadmin);
        btn_head1=findViewById(R.id.btnHead);
        btn_faimily1=findViewById(R.id.btnfaimily);



        sharePreferences = new MySharePreferences(this);

        head = sharePreferences.getheadlogin(Main_Menue.this);
        admin = sharePreferences.getLoginStatus(Main_Menue.this);

        Toast.makeText(this, "admin"+admin +"head"+head, Toast.LENGTH_SHORT).show();

        btn_admin1.setOnClickListener(this);
        btn_faimily1.setOnClickListener(this);
        btn_head1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.btnadmin:
                if(admin == true)
                {
                    startActivity(new Intent(Main_Menue.this, AdminPanel.class));
                }
                else
                startActivity(new Intent(Main_Menue.this, LoginActivity.class));
                break;
            case R.id.btnHead:
                if(head == true) {
                    startActivity(new Intent(Main_Menue.this, Worker_Disk.class));
                }
                else
                  startActivity(new Intent(Main_Menue.this, LoginActivity.class));
                break;
            case R.id.btnfaimily:

                    startActivity(new Intent(Main_Menue.this, Parent_Disk.class));
                 break;
        }
    }
}
