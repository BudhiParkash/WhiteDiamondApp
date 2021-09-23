package com.example.whitediamond.AdminApp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.AdminApp.adminadapter.WinLossUserAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.BookingPojo;
import com.example.whitediamond.model.PL_Bookings;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PLUserActivity extends AppCompatActivity {

    private TextView mBckfromPLuser;
    private RecyclerView mWinLossUserRecycle;
    private WinLossUserAdapter mWLUAdapter;

    private List<PL_Bookings> bookingPojoList = new ArrayList<>();

    private String token, game, date;
    private LinearLayoutManager manager;
    private ProgressBar mPlProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_l_user);
        initView();

        manager = new LinearLayoutManager(this);


        mWinLossUserRecycle.setHasFixedSize(true);
        mWinLossUserRecycle.setLayoutManager(manager);

        try {
            token = getIntent().getStringExtra("token");
            game = getIntent().getStringExtra("game");
            date = getIntent().getStringExtra("date");
        } catch (Exception e) {

        }

        mBckfromPLuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mPlProgressbar.setVisibility(View.VISIBLE);
        Call<List<PL_Bookings>> call = ApiClientInterface.getWDApiService().getWinnerLossUser(token, date, game);
        call.enqueue(new Callback<List<PL_Bookings>>() {
            @Override
            public void onResponse(Call<List<PL_Bookings>> call, Response<List<PL_Bookings>> response) {
                if (response.code() == 200) {


                    mPlProgressbar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    mWLUAdapter = new WinLossUserAdapter(PLUserActivity.this, bookingPojoList);
                    mWinLossUserRecycle.setAdapter(mWLUAdapter);
                    mWLUAdapter.notifyDataSetChanged();
                } else {
                    mPlProgressbar.setVisibility(View.GONE);
                    Toast.makeText(PLUserActivity.this, "No Win Loss User", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PL_Bookings>> call, Throwable t) {
                mPlProgressbar.setVisibility(View.GONE);
                Toast.makeText(PLUserActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        mBckfromPLuser = findViewById(R.id.bckfromPLuser);
        mWinLossUserRecycle = findViewById(R.id.winLossUserRecycle);
        mPlProgressbar = findViewById(R.id.plProgressbar);
    }
}