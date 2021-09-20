package com.example.whitediamond.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.BookingPojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BetHistoryAdapter extends RecyclerView.Adapter<BetHistoryAdapter.BetViewHolder> {


    private Context context;
    private List<BookingPojo> bookingPojoList;


    public BetHistoryAdapter(Context context, List<BookingPojo> bookingPojoList) {
        this.context = context;
        this.bookingPojoList = bookingPojoList;
    }

    @NonNull
    @Override
    public BetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bet_history, parent, false);
        return new BetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BetViewHolder holder, int position) {
        BookingPojo data = bookingPojoList.get(position);
        String betDate = data.getDate();
        String gameName = data.getGameName();
        int betPoint = data.getPointUsed();
        String bet_number = data.getNumberSelected();
        String currentDate;

        holder.mBetDate.setText(betDate);
        holder.mBetName.setText(gameName);
        holder.mBetNumber.setText(bet_number);
        holder.mBetPoint.setText(betPoint + "");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = sdf.format(new Date());


        if (data.getWinningProb()) {

            holder.mBetPointResult.setText(9 * betPoint + "");
           /* holder.mBetPoint.setBackgroundColor(context.getResources().getColor(R.color.greencolor));
            holder.mBetPoint.setTextColor(context.getResources().getColor(R.color.white));*/
        } else {
            if (betDate.equals(currentDate)) {
                holder.mBetPointResult.setText("" + betPoint);
            } else {
                holder.mBetPointResult.setText("-" + betPoint);
                /*holder.mBetPoint.setBackgroundColor(context.getResources().getColor(R.color.redcolor));
                holder.mBetPoint.setTextColor(context.getResources().getColor(R.color.white));*/
            }

        }

    }

    @Override
    public int getItemCount() {
        return bookingPojoList.size();
    }

    private void initView() {

    }


    public class BetViewHolder extends RecyclerView.ViewHolder {
        private TextView mBetDate;
        private TextView mBetName;
        private TextView mBetNumber;
        private TextView mBetPoint;
        private TextView mBetPointResult;

        public BetViewHolder(@NonNull View itemView) {
            super(itemView);

            mBetDate = itemView.findViewById(R.id.bet_date);
            mBetName = itemView.findViewById(R.id.bet_name);
            mBetNumber = itemView.findViewById(R.id.bet_number);
            mBetPoint = itemView.findViewById(R.id.bet_point);
            mBetPointResult = itemView.findViewById(R.id.bet_point_result);
        }
    }
}
