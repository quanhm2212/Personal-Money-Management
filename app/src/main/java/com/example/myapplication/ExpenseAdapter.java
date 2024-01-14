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

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {

    List<ExpenseList> expenseList;
    Pointer Acc;
    Context context;

    public ExpenseAdapter(List<ExpenseList> expenseList, Context context){
        this.expenseList = expenseList;
        this.context = context;
    }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_expense, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.viewDate.setText(expenseList.get(position).getDate());
            holder.viewCategory.setText(expenseList.get(position).getCategory());
            holder.viewAmount.setText(expenseList.get(position).getAmount().toString());
            holder.viewLocation.setText(expenseList.get(position).getLocation());
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExpenseFunction.class);
                    intent.putExtra("ExpenseID", expenseList.get(position).getExpenseID());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return expenseList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewDate;
        TextView viewCategory;
        TextView viewAmount;
        TextView viewLocation;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDate = itemView.findViewById(R.id.viewDate);
            viewCategory = itemView.findViewById(R.id.viewCategory);
            viewAmount = itemView.findViewById(R.id.viewAmount);
            viewLocation = itemView.findViewById(R.id.viewLocation);
            parentLayout = itemView.findViewById(R.id.oneLineExpense);
        }
    }
}
