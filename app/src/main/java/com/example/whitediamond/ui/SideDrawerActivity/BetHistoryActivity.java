package com.example.whitediamond.ui.SideDrawerActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.Adapter.BetHistoryAdapter;
import com.example.whitediamond.Adapter.ResultAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.BookingPojo;
import com.example.whitediamond.ui.Activity.SelectNumberActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BetHistoryActivity extends AppCompatActivity {

    private TextView mBckbetHistory;
    private RecyclerView mBetHistoryRecycle;
    private BetHistoryAdapter mBetHistoryAdapter;
    private ProgressBar mBethistoryProgressbar;
    private List<BookingPojo> bookingPojoList = new ArrayList<>();
    private LinearLayoutManager manager;

    private String token , userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_history);
        initView();
        try {
            token = getIntent().getStringExtra("token");
            userid = getIntent().getStringExtra("userid");
        }
        catch (Exception e){

        }
        manager = new LinearLayoutManager(this);


        mBetHistoryRecycle.setHasFixedSize(true);
        mBetHistoryRecycle.setLayoutManager(manager);

        mBckbetHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBethistoryProgressbar.setVisibility(View.VISIBLE);

        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getBooking(token , userid);
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {
                if(response.code() == 200){
                    mBethistoryProgressbar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    mBetHistoryAdapter = new BetHistoryAdapter(BetHistoryActivity.this , bookingPojoList);
                    mBetHistoryRecycle.setAdapter(mBetHistoryAdapter);
                    mBetHistoryAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(BetHistoryActivity.this, "No Bet History" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                Toast.makeText(BetHistoryActivity.this, "try after sometime", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        mBckbetHistory = findViewById(R.id.bckbetHistory);
        mBetHistoryRecycle = findViewById(R.id.betHistoryRecycle);
        mBethistoryProgressbar = findViewById(R.id.bethistoryProgressbar);
    }
}