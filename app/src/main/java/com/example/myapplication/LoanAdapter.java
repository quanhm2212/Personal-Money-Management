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

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.MyViewHolder> {

    List<LoanList> loanList;
    Pointer Acc;
    Context context;

    public LoanAdapter(List<LoanList> loanList, Context context){
        this.loanList = loanList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_loan, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.viewLoanStartDate.setText(loanList.get(position).getStartDate());
        holder.viewLoanDueDate.setText(loanList.get(position).getDueDate());
        holder.viewLoanCategory.setText(loanList.get(position).getCategory());
        holder.viewLoanAmount.setText(loanList.get(position).getAmount().toString());
        holder.viewLoanInterest.setText(loanList.get(position).getInterest().toString());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoanFunction.class);
                intent.putExtra("LoanID", loanList.get(position).getLoanID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewLoanStartDate;
        TextView viewLoanDueDate;
        TextView viewLoanCategory;
        TextView viewLoanAmount;
        TextView viewLoanInterest;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewLoanStartDate = itemView.findViewById(R.id.viewLoanStartDate);
            viewLoanDueDate = itemView.findViewById(R.id.viewLoanDueDate);
            viewLoanCategory = itemView.findViewById(R.id.viewLoanCategory);
            viewLoanAmount = itemView.findViewById(R.id.viewLoanAmount);
            viewLoanInterest = itemView.findViewById(R.id.viewLoanInterest);
            parentLayout = itemView.findViewById(R.id.oneLineLoan);
        }
    }
}
