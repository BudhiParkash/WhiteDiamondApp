package com.example.whitediamond.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.R;
import com.example.whitediamond.model.GamePojo;
import com.example.whitediamond.ui.Activity.SelectNumberActivity;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private Context context;
    private List<GamePojo> gamePojoList;

    public GameAdapter(Context context, List<GamePojo> gamePojoList) {
        this.context = context;
        this.gamePojoList = gamePojoList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_scheme_name, parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GamePojo data = gamePojoList.get(position);
        String gameName = data.getGameTitle();
        String gameResultTime = data.getStopTime();

        holder.mGameName.setText(gameName);
        holder.mResulttime.setText(gameResultTime);


    }

    @Override
    public int getItemCount() {
        return gamePojoList.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mGamelayout;
        private TextView mGameName;
        private TextView mResulttime;
        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            mGamelayout = itemView.findViewById(R.id.gamelayout);
            mGameName = itemView.findViewById(R.id.gameName);
            mResulttime = itemView.findViewById(R.id.resulttime);

            mGamelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GamePojo gameData = gamePojoList.get(getAdapterPosition());
                    String gameName = gameData.getGameTitle();
                    String resulttime = gameData.getStopTime();
                    Intent intent = new Intent(context , SelectNumberActivity.class);
                    intent.putExtra("gameName" , gameName);
                    intent.putExtra("resulttime" , resulttime);
                    context.startActivity(intent);
                }
            });
        }
    }
}