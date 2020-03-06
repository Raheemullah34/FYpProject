package com.example.fypproject.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fypproject.Model.ChildModel;
import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.Model.Worker;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Jaffar on 11/22/2016.
 */
public class VolleyService {

    Context mContext;

    public VolleyService(Context mContext) {
        this.mContext = mContext;
    }



/*Login Activity*/
public void teamLoginActivity(String url, final Worker workerObj, final VolleyResponseListener volleyResponseListener){
    try {
        final RequestQueue queue = Volley.newRequestQueue(mContext);

        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        volleyResponseListener.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyResponseListener.onError(volleyError);
                Log.v("see error responce",volleyError.toString());
            }
        })

        {


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.v("see error responce",response.toString());
                return super.parseNetworkResponse(response);


            }

            @Override
            protected Map<String, String> getParams(){
                HashMap<String, String> params = new HashMap<String, String>();


                params.put("email",workerObj.getWorkerName());
                params.put("password",workerObj.getWorkerPassword());




                return params;
            }
        };
        queue.add(req);



    }catch (Exception e){
        Log.v("see error responce",e.toString());

    }

}
/*Login Activity*/


    public void teamMateRegistration(String url, final Worker workerObj, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();

                    String workerName,workerContact,workerCnic,workerFatherName,WorkerJobStatus;
                    int workerTeam;
                    params.put("workerName",workerObj.getWorkerName());
                    params.put("workerContact",workerObj.getWorkerContact());
                    params.put("workerCnic",workerObj.getWorkerCnic());
                    params.put("workerFatherName",workerObj.getWorkerFatherName());
                    params.put("WorkerJobStatus",workerObj.getWorkerJobStatus());
                    params.put("workerTeam",workerObj.getWorkerTeam());
                    params.put("logginStatus",workerObj.getLoginStatus());



                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }




    /*Assign Duties*/
    public void assignDutties(String url, final DutiesModel dutiesModel, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();

                    String workerName,workerContact,workerCnic,workerFatherName,WorkerJobStatus;
                    int workerTeam;
                    params.put("duty_team_name",dutiesModel.getDuty_team_Name());
                    params.put("duty_city",dutiesModel.getDuty_city());
                    params.put("duty_address",dutiesModel.getDuty_address());
                    params.put("duty_date",dutiesModel.getDuty_date());




                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }
    /*Assign Duties*/

    /*Fetch Duties*/
    public void fetchDuties(String url, final DutiesModel dutiesModel, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();


                    params.put("duty_start",dutiesModel.getDuty_star());
                    params.put("duty_end",dutiesModel.getDuty_end());





                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }
    /*Fetch Duties*/

/*fetch duties by team name*/

    public void fetchTeamDuties(String url, final DutiesModel dutiesModel, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();


                    params.put("duty_start",dutiesModel.getDuty_star());
                    params.put("duty_end",dutiesModel.getDuty_end());
                    params.put("team_name",dutiesModel.getDuty_team_Name());





                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }
/*fetch duties by team name*/
    /*Child Registration*/
    public void childRegistration(String url, final ChildModel workerObj, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();

                    String workerName,workerContact,workerCnic,workerFatherName,WorkerJobStatus;
                    int workerTeam;
                    params.put("child_name",workerObj.getChild_name());
                    params.put("child_father_name",workerObj.getChild_father_name());
                    params.put("child_father_cnic",workerObj.getFather_cnic());
                    params.put("child_family_no",workerObj.getChild_family_no());
                    params.put("child_location",workerObj.getChild_location());
                    params.put("child_reg_date",workerObj.getChild_reg_date());
                    params.put("entery_person_type",workerObj.getEntery_person_type());



                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }
    /*Child Registration*/


    /*fetch Locations*/
    public void fetchHeadLocations(String url, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();








                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }
    /*fetch Locations*/

    /*sendReportByHead*/

    public void postReportByHead(String url, final DutiesModel workerObj, final VolleyResponseListener volleyResponseListener){
        try {
            final RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest req = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            volleyResponseListener.onSuccess(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyResponseListener.onError(volleyError);
                    Log.v("see error responce",volleyError.toString());
                }
            })

            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    Log.v("see error responce",response.toString());
                    return super.parseNetworkResponse(response);


                }

                @Override
                protected Map<String, String> getParams(){
                    HashMap<String, String> params = new HashMap<String, String>();

                    String workerName,workerContact,workerCnic,workerFatherName,WorkerJobStatus;
                    int workerTeam;

                    http://alefcabs.com/dev/apis/pax_login.php?pax_mobile_number=0303&pax_password=alifcabs
                    params.put("id",workerObj.getId());
                    params.put("duty_des",workerObj.getDuty_des());
                    params.put("status",workerObj.getStatus());




                    return params;
                }
            };
            queue.add(req);



        }catch (Exception e){
            Log.v("see error responce",e.toString());

        }

    }

    /*sendReportByHead*/


    /*------------------------------------------------- /For Headers----------------------------------------------------------*/
    public interface VolleyResponseListener {
        void onSuccess(String response);
        void onError(VolleyError error);
    }

}
