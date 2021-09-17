package com.example.whitediamond.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.NumberPojo;
import com.example.whitediamond.ui.Activity.MoneyActivity;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {
    private Context context;
    private List<NumberPojo> numberPojoList;
    private String gameName;

    public NumberAdapter(Context context, List<NumberPojo> numberPojoList,String gameName) {
        this.context = context;
        this.numberPojoList = numberPojoList;
        this.gameName = gameName;
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


    public class NumberViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtnumber;
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtnumber = itemView.findViewById(R.id.txtnumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NumberPojo data = numberPojoList.get(getAdapterPosition());
                    String number = data.getNumber();

                    Intent intent = new Intent(context , MoneyActivity.class);
                    intent.putExtra("number" , number);
                    intent.putExtra("gameName" , gameName);
                    context.startActivity(intent);
                }
            });

        }
    }
}
