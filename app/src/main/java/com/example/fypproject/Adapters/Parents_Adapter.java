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
import com.example.fypproject.Model.Parents_Model;
import com.example.fypproject.R;
import com.example.fypproject.ViewParentFeedBack;

import java.util.ArrayList;

public class Parents_Adapter extends RecyclerView.Adapter<Parents_Adapter.MyViewHolder> {

    private ArrayList<Parents_Model> dutiesModels;
    Context context;

    public Parents_Adapter(ArrayList<Parents_Model> dutiesModels, Context context) {
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

        final Parents_Model parents_model = dutiesModels.get(position);
        holder.tv_teamName.setText(parents_model.getFeedbac_family_number());
        holder.tv_location.setText(parents_model.getFeedback_address());
       /* if (parents_model.getFeedbac_status().matches("0")){
            holder.tv_status.setText("Pending");
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.red));
        }
        else {

            holder.tv_status.setText("Done");
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.green));
        }*/


       holder.tv_status.setText(parents_model.getFeedback_father_name());
        holder.tv_date.setText("Assign Date :"+parents_model.getFeedback_date());

        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ViewParentFeedBack.class).putExtra("team_name",parents_model.getFeedback_father_name())
                .putExtra("fathername",parents_model.getFeedback_father_name())
                .putExtra("familyno",parents_model.getFeedbac_family_number())
                .putExtra("des",parents_model.getFeedback_decrition())
                .putExtra("address",parents_model.getFeedback_address())
                .putExtra("date",parents_model.getFeedback_date()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return dutiesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_teamName, tv_status, tv_location,tv_date;
        public LinearLayout header;

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
