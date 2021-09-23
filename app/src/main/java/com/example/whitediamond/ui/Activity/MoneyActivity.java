package com.example.whitediamond.ui.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.BookingPojo;
import com.example.whitediamond.model.UserPojo;
import com.example.whitediamond.ui.Fragments.DoneDialogueFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoneyActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private TextView mBckfrommoney;
    private TextView mGameNameM;
    private EditText mEtxPoints;
    private TextView mTxt10;
    private TextView mTxt20;
    private TextView mTxt50;
    private TextView mTxt100;
    private TextView mTxt200;
    private TextView mTxt1000;
    private TextView mTxt2000;
    private TextView mTxt5000;

    private String gameName;
    private String number;
    private String userID;
    private String bookingdate;
    private String tokken;
    private TextView mTxtnumbermoney;
    private ProgressBar mMoneyProgressbar;
    private int totaldiamondPoint;
    private TextView mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        try {
            tokken = prefs.getString("tokken", "no tokkens");
            userID = prefs.getString("userId", "null");
            totaldiamondPoint = prefs.getInt("dp", 0);

        }
        catch (Exception e){

        }

        mPoints.setText(totaldiamondPoint+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        bookingdate = sdf.format(new Date());
        try {
            gameName = getIntent().getStringExtra("gameName");
            number = getIntent().getStringExtra("number");
        } catch (Exception e) {

        }

        mTxtnumbermoney.setText(number);
        mGameNameM.setText(gameName);

        mBckfrommoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mTxt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt10.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        mTxt20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt20.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        mTxt50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt50.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        mTxt100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt100.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        mTxt200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt200.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        mTxt1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt1000.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        mTxt2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt2000.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        mTxt5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtxPoints.setText(mTxt5000.getText().toString());
                mEtxPoints.requestFocus();
                mTxt10.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt20.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt50.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt100.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt200.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt1000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt2000.setBackgroundColor(getResources().getColor(R.color.white));
                mTxt5000.setBackgroundColor(getResources().getColor(R.color.primaryTextColor));
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betPoints = mEtxPoints.getText().toString();
                if (betPoints.length() == 1) {
                    mEtxPoints.setError("Minimum value is 10");
                    mEtxPoints.requestFocus();
                } else if (betPoints.isEmpty()) {
                    mEtxPoints.setError("Minimum value is 10");
                    mEtxPoints.requestFocus();
                } else if (Integer.parseInt(betPoints) > totaldiamondPoint) {
                    mEtxPoints.setError("You have not sufficent balance");
                    mEtxPoints.requestFocus();
                } else {
                    donebooking(betPoints);
                }


            }
        });
    }

    private void donebooking(String finalPoints) {

        mMoneyProgressbar.setVisibility(View.VISIBLE);
        int points = Integer.parseInt(finalPoints);


        BookingPojo bookingPojo = new BookingPojo(gameName, bookingdate, number, points, userID);
        Call<Void> call = ApiClientInterface.getWDApiService().postbooking(tokken, bookingPojo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    mMoneyProgressbar.setVisibility(View.GONE);
                    DoneDialogueFragment bookingDialogueFragment = new DoneDialogueFragment();
                    bookingDialogueFragment.show(getSupportFragmentManager(), "Donefragment");
                    bookingDialogueFragment.setCancelable(false);
                } else {
                    mMoneyProgressbar.setVisibility(View.GONE);
                    Toast.makeText(MoneyActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mMoneyProgressbar.setVisibility(View.GONE);
                Toast.makeText(MoneyActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        mSubmitButton = findViewById(R.id.submitButton);
        mBckfrommoney = findViewById(R.id.bckfrommoney);
        mGameNameM = findViewById(R.id.gameNameM);
        mEtxPoints = findViewById(R.id.etxPoints);
        mTxt10 = findViewById(R.id.txt10);
        mTxt20 = findViewById(R.id.txt20);
        mTxt50 = findViewById(R.id.txt50);
        mTxt100 = findViewById(R.id.txt100);
        mTxt200 = findViewById(R.id.txt200);
        mTxt1000 = findViewById(R.id.txt1000);
        mTxt2000 = findViewById(R.id.txt2000);
        mTxt5000 = findViewById(R.id.txt5000);
        mTxtnumbermoney = findViewById(R.id.txtnumbermoney);
        mMoneyProgressbar = findViewById(R.id.moneyProgressbar);
        mPoints = findViewById(R.id.points);
    }
}