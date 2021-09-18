package com.example.whitediamond.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.NumberPojo;
import com.example.whitediamond.ui.Activity.MoneyActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {
    private Context context;
    private List<NumberPojo> numberPojoList;
    private String gameName;
    private String resulttime;


    public NumberAdapter(Context context, List<NumberPojo> numberPojoList, String gameName, String resulttime) {
        this.context = context;
        this.numberPojoList = numberPojoList;
        this.gameName = gameName;
        this.resulttime = resulttime;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_numbers, parent, false);
        return new NumberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        NumberPojo data = numberPojoList.get(position);
        String number = data.getNumber();
        holder.mTxtnumber.setText(number);

    }

    @Override
    public int getItemCount() {
        return numberPojoList.size();
    }

    private void initView() {

    }


    public class NumberViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtnumber;
        private String serverTime;
        private int currentTime;
        private ImageView mLocknumber;
        private LinearLayout mLayoutNumber;

        private int endtime;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtnumber = itemView.findViewById(R.id.txtnumber);
            mLocknumber = itemView.findViewById(R.id.locknumber);
            mLayoutNumber = itemView.findViewById(R.id.layoutNumber);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NumberPojo data = numberPojoList.get(getAdapterPosition());
                    String number = data.getNumber();

                    Intent intent = new Intent(context, MoneyActivity.class);
                    intent.putExtra("number", number);
                    intent.putExtra("gameName", gameName);
                    context.startActivity(intent);
                }
            });

            String[] split1 = resulttime.split(" ");
            if (resulttime.contains("AM")) {
                endtime = Integer.parseInt(split1[0]);
            } else if (resulttime.contains("PM")) {
                endtime = Integer.parseInt(split1[0]);
                if (endtime != 12) {
                    endtime = endtime + 12;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("h a");
            serverTime = sdf.format(new Date());
            String[] split = serverTime.split(" ");
            if (serverTime.contains("am")) {
                    currentTime = Integer.parseInt(split[0]);
            } else if (serverTime.contains("pm")) {
                currentTime = Integer.parseInt(split[0]);
                if (currentTime != 12) {
                    currentTime = currentTime + 12;
                }
            }

            if(currentTime < endtime){
                mLayoutNumber.setVisibility(View.VISIBLE);
                mLocknumber.setVisibility(View.GONE);
            }
            else {
                mLayoutNumber.setVisibility(View.GONE);
                mLocknumber.setVisibility(View.VISIBLE);
                itemView.setClickable(false);
            }





        }
    }
}
