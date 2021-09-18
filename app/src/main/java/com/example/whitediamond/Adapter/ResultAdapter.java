package com.example.whitediamond.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.ResultPojo;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private Context context;
    private List<ResultPojo> resultPojoList;

    public ResultAdapter(Context context, List<ResultPojo> resultPojoList) {
        this.context = context;
        this.resultPojoList = resultPojoList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_result, parent, false);
        return new ResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        ResultPojo data = resultPojoList.get(position);
         String resultNumber = data.getWinnerNumber();
         String resultDate = data.getDateOfResult();

         holder.mResultDate.setText(resultDate);
         holder.mResultNumber.setText(resultNumber);

    }

    @Override
    public int getItemCount() {
        return resultPojoList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mResultDate;
        private TextView mResultNumber;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            mResultDate = itemView.findViewById(R.id.resultDate);
            mResultNumber = itemView.findViewById(R.id.resultNumber);
        }
    }
}
