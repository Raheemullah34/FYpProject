package com.example.fypproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fypproject.Model.DutiesModel;
import com.example.fypproject.R;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private ArrayList<DutiesModel> dutiesModels;
    Context context;

    public LocationAdapter(ArrayList<DutiesModel> dutiesModels, Context context) {
        this.dutiesModels = dutiesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.head_location_listview, parent, false);

        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DutiesModel dutiesModel = dutiesModels.get(position);
        holder.tv_teamName.setText(dutiesModel.getDuty_team_Name());
        holder.tv_location.setText(dutiesModel.getDuty_address());

        holder.tv_teamName.setText(dutiesModel.getDuty_team_Name());
        holder.tv_location.setText(dutiesModel.getDuty_address());


    }

    @Override
    public int getItemCount() {
        return dutiesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_teamName, tv_status, tv_location,tv_date;

        public MyViewHolder(View view) {
            super(view);
            tv_teamName = (TextView) view.findViewById(R.id.tv_teamName);


            tv_location = (TextView) view.findViewById(R.id.tv_location);
        }
    }
}
