package com.example.fypproject.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fypproject.Model.Worker;
import com.google.gson.Gson;

public class MySharePreferences {
    Context context;

    public MySharePreferences() {
    }

    public MySharePreferences(Context context) {
        this.context = context;
    }

    public static final String MyPREFERENCES = "polioShareReferences";
    public static SharedPreferences.Editor editor;
    private SharedPreferences sharedpreferences;


    public void SaveUserData(Context context , Worker user){


        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String mydata=gson.toJson(user);
        editor.putString("userData",mydata);
        editor.commit();

    }


    public Worker getUserData(Context ctx){

        Worker userdatahere=new Worker();
        SharedPreferences prfs = ctx.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);

        Gson gson = new Gson();
        String json = prfs.getString("userData", "");
        userdatahere = gson.fromJson(json, Worker.class);
        return userdatahere;
    }


/*save Login Status*/

    public Boolean getLoginStatus(Context ctx){

        SharedPreferences prfs = ctx.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        Boolean link=prfs.getBoolean("loginstatus",false);
        return link;
    }

    public void saveLoginStatus(Context context,boolean status){

        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        editor = sharedpreferences.edit();
        editor.putBoolean("loginstatus", status);
        editor.commit();

    }

    /*save Login Status*/
    /*save head Status*/

    public Boolean getheadlogin(Context ctx){

        SharedPreferences prfs = ctx.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        Boolean link=prfs.getBoolean("headlogin",false);
        return link;
    }

    public void saveheadlogin(Context context,boolean status){

        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        editor = sharedpreferences.edit();
        editor.putBoolean("headlogin", status);
        editor.commit();

    }

    /*save head Status*/

}
