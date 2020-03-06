package com.example.fypproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.R;
import com.example.fypproject.ViewDutiesDescriptions;
import com.example.fypproject.ViewParentFeedBack;

import java.util.ArrayList;

public class Duties_adapter extends RecyclerView.Adapter<Duties_adapter.MyViewHolder> {

    private ArrayList<DutiesModel> dutiesModels;
    Context context;

    public Duties_adapter(ArrayList<DutiesModel> dutiesModels, Context context) {
        this.dutiesModels = dutiesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.duties_listview, parent, false);

        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DutiesModel dutiesModel = dutiesModels.get(position);
        holder.tv_teamName.setText(dutiesModel.getDuty_team_Name());
        holder.tv_location.setText(dutiesModel.getDuty_address());
        if (dutiesModel.getStatus().matches("0")){
            holder.tv_status.setText("Pending");
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.red));
        }
        else {

            holder.tv_status.setText("Done");
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.green));
        }

        holder.tv_date.setText("Assign Date :"+dutiesModel.getDuty_date());


        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ViewDutiesDescriptions.class)
                        .putExtra("team_name",dutiesModel.getDuty_team_Name())
                        .putExtra("address",dutiesModel.getDuty_address())
                        .putExtra("duty_status",dutiesModel.getStatus())
                        .putExtra("duty_date",dutiesModel.getDuty_date())
                        .putExtra("duty_des",dutiesModel.getDuty_des()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return dutiesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_teamName, tv_status, tv_location,tv_date;
        LinearLayout header;

        public MyViewHolder(View view) {
            super(view);
            tv_teamName = (TextView) view.findViewById(R.id.tv_teamName);

            tv_status = (TextView) view.findViewById(R.id.tv_status);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_location = (TextView) view.findViewById(R.id.tv_location);
            header = (LinearLayout) view.findViewById(R.id.header);
        }
    }
}
