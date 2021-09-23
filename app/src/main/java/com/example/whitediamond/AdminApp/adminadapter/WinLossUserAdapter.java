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
import com.example.whitediamond.model.PL_Bookings;

import java.util.List;

public class WinLossUserAdapter extends RecyclerView.Adapter<WinLossUserAdapter.WLUviewHolder> {

    private Context context;
    private List<PL_Bookings> bookingPojoList;
    private TextView mWinnerUserID;


    public WinLossUserAdapter(Context context, List<PL_Bookings> bookingPojoList) {
        this.context = context;
        this.bookingPojoList = bookingPojoList;
    }

    @NonNull
    @Override
    public WLUviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_win_loss_user, parent, false);
        return new WLUviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WLUviewHolder holder, int position) {

        PL_Bookings data = bookingPojoList.get(position);
        boolean winner = data.getWinningProb();
        int point = data.getPointUsed();
        String userid = data.getUserId().getEmail();

        if(winner){
            mWinnerAmount.setText("+" + 9 * point );
           mWinnerUserID.setText(userid);

        }
        else {
            mLossAmount.setText("-"+point);
           mLossUserID.setText(userid);

        }

    }

    @Override
    public int getItemCount() {
        return bookingPojoList.size();
    }
    private TextView mWinnerAmount;
    private TextView mLossUserID;
    private TextView mLossAmount;

    public class WLUviewHolder extends RecyclerView.ViewHolder {
        public WLUviewHolder(@NonNull View itemView) {
            super(itemView);

            mWinnerUserID = itemView.findViewById(R.id.winner_User_ID);
            mWinnerAmount = itemView.findViewById(R.id.winner_Amount);
            mLossUserID = itemView.findViewById(R.id.loss_user_ID);
            mLossAmount = itemView.findViewById(R.id.loss_amount);
        }
    }
}
