package com.example.whitediamond.ui.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.Adapter.NumberAdapter;
import com.example.whitediamond.Adapter.ResultAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.NumberPojo;
import com.example.whitediamond.model.ResultPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectNumberActivity extends AppCompatActivity {

    private String gameName;
    private TextView mGameName;
    private NumberAdapter mNumberAdapter;
    private List<NumberPojo> numberPojoList = new ArrayList<>();
    private TextView mBcknumber;
    private RecyclerView mNumberRecycle;
    private GridLayoutManager mLayoutManager;
    private String resulttime;
    private TextView mResultgameName;
    private RecyclerView mResultRecycle;
    private ResultAdapter mResultAdapter;
    private List<ResultPojo> resultPojos = new ArrayList<>();
    LinearLayoutManager manager;
    private String tokken;
    private TextView mNoresult;
    private ProgressBar mNumberProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        tokken = prefs.getString("tokken", "no tokkens");

        mLayoutManager = new GridLayoutManager(SelectNumberActivity.this, 3);
        mNumberRecycle.setLayoutManager(mLayoutManager);

        manager = new LinearLayoutManager(this);


        mResultRecycle.setHasFixedSize(true);
        mResultRecycle.setLayoutManager(manager);


        try {
            resulttime = getIntent().getStringExtra("resulttime");
            gameName = getIntent().getStringExtra("gameName");
        } catch (Exception e) {

        }

        mResultgameName.setText(gameName + " :- विजेता नंबर लिस्ट");

        mGameName.setText(gameName);

        mBcknumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        numberPojoList.add(new NumberPojo("0"));
        numberPojoList.add(new NumberPojo("1"));
        numberPojoList.add(new NumberPojo("2"));
        numberPojoList.add(new NumberPojo("3"));
        numberPojoList.add(new NumberPojo("4"));
        numberPojoList.add(new NumberPojo("5"));
        numberPojoList.add(new NumberPojo("6"));
        numberPojoList.add(new NumberPojo("7"));
        numberPojoList.add(new NumberPojo("8"));
        numberPojoList.add(new NumberPojo("9"));

        mNumberAdapter = new NumberAdapter(SelectNumberActivity.this, numberPojoList, gameName, resulttime);
        mNumberRecycle.setAdapter(mNumberAdapter);
        mNumberAdapter.notifyDataSetChanged();


        getResult();
    }

    private void getResult() {
        mNumberProgressbar.setVisibility(View.VISIBLE);
        Call<List<ResultPojo>> call = ApiClientInterface.getWDApiService().getResult(tokken, gameName);
        call.enqueue(new Callback<List<ResultPojo>>() {
            @Override
            public void onResponse(Call<List<ResultPojo>> call, Response<List<ResultPojo>> response) {
                if (response.code() == 200) {
                    resultPojos = response.body();
                    mNumberProgressbar.setVisibility(View.GONE);
                    mResultAdapter = new ResultAdapter(SelectNumberActivity.this, resultPojos);
                    mResultRecycle.setAdapter(mResultAdapter);
                    mResultAdapter.notifyDataSetChanged();
                    mNumberProgressbar.setVisibility(View.GONE);
                } else if (response.code() == 404) {
                    mNumberProgressbar.setVisibility(View.GONE);
                    mNoresult.setVisibility(View.VISIBLE);
                } else {
                    mNumberProgressbar.setVisibility(View.GONE);
                    Toast.makeText(SelectNumberActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResultPojo>> call, Throwable t) {
                mNumberProgressbar.setVisibility(View.GONE);
                Toast.makeText(SelectNumberActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mGameName = findViewById(R.id.gameName);
        mBcknumber = findViewById(R.id.bcknumber);
        mNumberRecycle = findViewById(R.id.numberRecycle);
        mResultgameName = findViewById(R.id.resultgameName);
        mResultRecycle = findViewById(R.id.resultRecycle);
        mNoresult = findViewById(R.id.noresult);
        mNumberProgressbar = findViewById(R.id.numberProgressbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}