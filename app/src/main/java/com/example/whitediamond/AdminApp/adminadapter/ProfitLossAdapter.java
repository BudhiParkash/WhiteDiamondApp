package com.example.whitediamond.AdminApp.adminadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.BookingPojo;

import java.util.List;

public class ProfitLossAdapter extends RecyclerView.Adapter<ProfitLossAdapter.PLViewHolder> {

    private Context context;
    private List<BookingPojo> bookingPojoList;

    public ProfitLossAdapter(Context context, List<BookingPojo> bookingPojoList) {
        this.context = context;
        this.bookingPojoList = bookingPojoList;
    }

    @NonNull
    @Override
    public PLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profit_loss, parent, false);
        return new PLViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PLViewHolder holder, int position) {
        BookingPojo data = bookingPojoList.get(position);
        String number = data.getNumberSelected();
        int totalAmount = data.getPointUsed();
        int profiteloss = data.getPointUsed();

        holder.mResultNumber.setText(number);
        holder.mActualAmount.setText(totalAmount+"");

    }

    @Override
    public int getItemCount() {
        return bookingPojoList.size();
    }

    public class PLViewHolder extends RecyclerView.ViewHolder {
        private TextView mResultNumber;
        private TextView mActualAmount;
        private TextView mProfitLosssAmount;
        public PLViewHolder(@NonNull View itemView) {
            super(itemView);

            mResultNumber = itemView.findViewById(R.id.result_Number);
            mActualAmount = itemView.findViewById(R.id.actual_amount);
            mProfitLosssAmount = itemView.findViewById(R.id.profitLosss_amount);
        }
    }
}
