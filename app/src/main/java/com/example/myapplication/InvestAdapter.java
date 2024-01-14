package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvestAdapter extends RecyclerView.Adapter<InvestAdapter.MyViewHolder> {

    List<InvestList> investList;
    Pointer Acc;
    Context context;

    public InvestAdapter(List<InvestList> investList, Context context){
        this.investList = investList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_invest, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.viewInvestStartDate.setText(investList.get(position).getStartDate());
        holder.viewInvestCategory.setText(investList.get(position).getCategory());
        holder.viewInvestAmount.setText(investList.get(position).getAmount().toString());
        holder.viewInvestInterest.setText(investList.get(position).getInterest().toString());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvestmentFunction.class);
                intent.putExtra("InvestID", investList.get(position).getInvestID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return investList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewInvestStartDate;
        TextView viewInvestCategory;
        TextView viewInvestAmount;
        TextView viewInvestInterest;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewInvestStartDate = itemView.findViewById(R.id.viewInvestStartDate);
            viewInvestCategory = itemView.findViewById(R.id.viewInvestCategory);
            viewInvestAmount = itemView.findViewById(R.id.viewInvestAmount);
            viewInvestInterest = itemView.findViewById(R.id.viewInvestInterest);
            parentLayout = itemView.findViewById(R.id.oneLineInvest);
        }
    }
}
