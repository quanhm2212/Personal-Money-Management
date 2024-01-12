package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.MyViewHolder> {

    List<BudgetList> budgetList;
    Pointer Acc;
    Context context;

    public BudgetAdapter(List<BudgetList> budgetList, Context context) {
        this.budgetList = budgetList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_budget, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.viewGoal.setText(budgetList.get(position).getGoal());
        holder.viewStatus.setText(budgetList.get(position).getStatus());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BudgetFunction.class);
                intent.putExtra("BudgetID", budgetList.get(position).getBudgetID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return budgetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewGoal;
        TextView viewStatus;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewGoal = itemView.findViewById(R.id.viewGoal);
            viewStatus = itemView.findViewById(R.id.viewStatus);
            parentLayout = itemView.findViewById(R.id.oneLineBudget);
        }
    }
}
