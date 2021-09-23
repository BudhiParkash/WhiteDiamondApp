package com.example.whitediamond.AdminApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whitediamond.AdminApp.adminadapter.ProfitLossAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.BookingPojo;
import com.example.whitediamond.model.ResultPojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfitLossActivity extends AppCompatActivity {

    private TextView mBckfromPL;
    private ProgressBar mPlProgressBar;
    // private RecyclerView mProfitLossRecycle;
    private List<BookingPojo> bookingPojoList = new ArrayList<>();
    private LinearLayoutManager manager;
    private ProfitLossAdapter mProfiteLossAdapter;

    private String tokken;
    private String gameName;
    private String currentDate;
    private TableLayout mTableHeadingLayout;
    private TextView mNumber0;
    private TextView mTotalAmount0;
    private TextView mProfitLosssAmount0;
    private TextView mNumber1;
    private TextView mTotalAmount1;
    private TextView mProfitLosssAmount1;
    private TextView mNumber2;
    private TextView mTotalAmount2;
    private TextView mProfitLosssAmount2;
    private TextView mNumber3;
    private TextView mTotalAmount3;
    private TextView mProfitLosssAmount3;
    private TextView mNumber4;
    private TextView mTotalAmount4;
    private TextView mProfitLosssAmount4;
    private TextView mNumber5;
    private TextView mTotalAmount5;
    private TextView mProfitLosssAmount5;
    private TextView mNumber6;
    private TextView mTotalAmount6;
    private TextView mProfitLosssAmount6;
    private TextView mNumber7;
    private TextView mTotalAmount7;
    private TextView mProfitLosssAmount7;
    private TextView mNumber8;
    private TextView mTotalAmount8;
    private TextView mProfitLosssAmount8;
    private TextView mNumber9;
    private TextView mTotalAmount9;
    private TextView mProfitLosssAmount9;

    int totalamount0 = 0;
    int totalamount1 = 0;
    int totalamount2 = 0;
    int totalamount3 = 0;
    int totalamount4 = 0;
    int totalamount5 = 0;
    int totalamount6 = 0;
    int totalamount7 = 0;
    int totalamount8 = 0;
    int totalamount9 = 0;


    int totalsum = 0;
    private EditText mEtxResultNumber;
    private Button mDeclareResult;
    private TextView mProfitlossuser;
    private TextView mGName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_loss);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        tokken = prefs.getString("tokken", "no tokkens");
        manager = new LinearLayoutManager(this);
        try {
            gameName = getIntent().getStringExtra("gameName");
        } catch (Exception e) {

        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = sdf.format(new Date());

//        mProfitLossRecycle.setHasFixedSize(true);
//        mProfitLossRecycle.setLayoutManager(manager);

        mBckfromPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mGName.setText(gameName+ " - " + currentDate);


        mProfitlossuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfitLossActivity.this, PLUserActivity.class);
                intent.putExtra("game", gameName);
                intent.putExtra("date", currentDate);
                intent.putExtra("token", tokken);
                startActivity(intent);
                finish();

            }
        });

        mDeclareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultNumber = mEtxResultNumber.getText().toString();
                if (TextUtils.isEmpty(resultNumber)) {
                    mEtxResultNumber.setError("Please enter result Number");
                    mEtxResultNumber.requestFocus();
                    return;
                } else {
                    publishResult(resultNumber, gameName, currentDate);
                }
            }
        });


    }

    private void publishResult(String resultNumber, String gameName, String currentDate) {
        mPlProgressBar.setVisibility(View.VISIBLE);
        ResultPojo resultPojo = new ResultPojo(gameName, resultNumber, currentDate);
        Call<Void> call = ApiClientInterface.getWDApiService().createResult(tokken, resultPojo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    mPlProgressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfitLossActivity.this, "Result is published", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfitLossActivity.this, "Failed to published Result ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getResult3() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "3");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount3 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue3("3", totalamount3);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue3("3", totalamount3);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue3(String s, int totalamount3) {
        mNumber3.setText(s);
        mTotalAmount3.setText(totalamount3 + "");
    }

    private void getResult4() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "4");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount4 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue4("4", totalamount4);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue4("4", totalamount4);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue4(String s, int totalamount4) {
        mNumber4.setText(s);
        mTotalAmount4.setText(totalamount4 + "");
    }

    private void getResult5() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "5");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount5 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue5("5", totalamount5);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue5("5", totalamount5);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue5(String s, int totalamount5) {
        mNumber5.setText(s);
        mTotalAmount5.setText(totalamount5 + "");
    }

    private void getResult6() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "6");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount6 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue6("6", totalamount6);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue6("6", totalamount6);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue6(String s, int totalamount6) {
        mNumber6.setText(s);
        mTotalAmount6.setText(totalamount6 + "");
    }

    private void getResult7() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "7");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount7 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue7("7", totalamount7);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue7("7", totalamount7);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue7(String s, int totalamount7) {
        mNumber7.setText(s);
        mTotalAmount7.setText(totalamount7 + "");
    }

    private void getResult8() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "8");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount8 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue8("8", totalamount8);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue8("8", totalamount8);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue8(String s, int totalamount8) {
        mNumber8.setText(s);
        mTotalAmount8.setText(totalamount8 + "");
    }


    private void getResult9() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "9");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount9 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue9("9", totalamount9);

                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue9("9", totalamount9);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue9(String s, int totalamount9) {
        mNumber9.setText(s);
        mTotalAmount9.setText(totalamount9 + "");

        mPlProgressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPlProgressBar.setVisibility(View.GONE);
                totalsum = totalamount0 + totalamount1 + totalamount2 + totalamount3 + totalamount4 + totalamount5 + totalamount6 + totalamount7 + totalamount8 + totalamount9;
                setProfitLoss(totalsum);
            }
        }, 1000);

    }

    private void setProfitLoss(int totalsum) {
        mProfitLosssAmount1.setText(totalsum - 9 * totalamount1 + "");
        mProfitLosssAmount2.setText(totalsum - 9 * totalamount2 + "");
        mProfitLosssAmount3.setText(totalsum - 9 * totalamount3 + "");
        mProfitLosssAmount4.setText(totalsum - 9 * totalamount4 + "");
        mProfitLosssAmount5.setText(totalsum - 9 * totalamount5 + "");
        mProfitLosssAmount6.setText(totalsum - 9 * totalamount6 + "");
        mProfitLosssAmount7.setText(totalsum - 9 * totalamount7 + "");
        mProfitLosssAmount8.setText(totalsum - 9 * totalamount8 + "");
        mProfitLosssAmount9.setText(totalsum - 9 * totalamount9 + "");
        mProfitLosssAmount0.setText(totalsum - 9 * totalamount0 + "");


    }


    private void getResult2() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "2");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount2 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue2("2", totalamount2);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue2("2", totalamount2);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue2(String s, int totalamount2) {
        mNumber2.setText(s);
        mTotalAmount2.setText(totalamount2 + "");
    }

    private void getResult1() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "1");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount1 += bookingPojoList.get(i).getPointUsed();
                    }
                    setValue1("1", totalamount1);
                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue1("1", totalamount1);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue1(String s, int totalamount1) {
        mNumber1.setText(s);
        mTotalAmount1.setText(totalamount1 + "");
    }

    private void getResult0() {
        mPlProgressBar.setVisibility(View.VISIBLE);
        Call<List<BookingPojo>> call = ApiClientInterface.getWDApiService().getResultBoking(tokken, currentDate, gameName, "0");
        call.enqueue(new Callback<List<BookingPojo>>() {
            @Override
            public void onResponse(Call<List<BookingPojo>> call, Response<List<BookingPojo>> response) {

                if (response.code() == 200) {
                    mPlProgressBar.setVisibility(View.GONE);
                    bookingPojoList = response.body();
                    for (int i = 0; i < bookingPojoList.size(); i++) {
                        totalamount0 += bookingPojoList.get(i).getPointUsed();

                    }
                    setValue0("0", totalamount0);

                } else {
                    mPlProgressBar.setVisibility(View.GONE);
                    setValue0("0", totalamount0);
                }
            }


            @Override
            public void onFailure(Call<List<BookingPojo>> call, Throwable t) {
                mPlProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProfitLossActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValue0(String s, int totalamount0) {
        mNumber0.setText(s);
        mTotalAmount0.setText(totalamount0 + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getResult0();
        getResult1();
        getResult2();
        getResult3();
        getResult4();
        getResult5();
        getResult6();
        getResult7();
        getResult8();
        getResult9();
    }

    private void initView() {
        mBckfromPL = findViewById(R.id.bckfromPL);
        mPlProgressBar = findViewById(R.id.plProgressBar);
        //  mProfitLossRecycle = findViewById(R.id.profitLossRecycle);
        mTableHeadingLayout = findViewById(R.id.table_heading_layout);
        mNumber0 = findViewById(R.id.Number0);
        mTotalAmount0 = findViewById(R.id.total_amount_0);
        mProfitLosssAmount0 = findViewById(R.id.profitLosss_amount_0);
        mNumber1 = findViewById(R.id.Number1);
        mTotalAmount1 = findViewById(R.id.total_amount_1);
        mProfitLosssAmount1 = findViewById(R.id.profitLosss_amount_1);
        mNumber2 = findViewById(R.id.Number2);
        mTotalAmount2 = findViewById(R.id.total_amount_2);
        mProfitLosssAmount2 = findViewById(R.id.profitLosss_amount_2);
        mNumber3 = findViewById(R.id.Number3);
        mTotalAmount3 = findViewById(R.id.total_amount_3);
        mProfitLosssAmount3 = findViewById(R.id.profitLosss_amount_3);
        mNumber4 = findViewById(R.id.Number4);
        mTotalAmount4 = findViewById(R.id.total_amount_4);
        mProfitLosssAmount4 = findViewById(R.id.profitLosss_amount_4);
        mNumber5 = findViewById(R.id.Number5);
        mTotalAmount5 = findViewById(R.id.total_amount_5);
        mProfitLosssAmount5 = findViewById(R.id.profitLosss_amount_5);
        mNumber6 = findViewById(R.id.Number6);
        mTotalAmount6 = findViewById(R.id.total_amount_6);
        mProfitLosssAmount6 = findViewById(R.id.profitLosss_amount_6);
        mNumber7 = findViewById(R.id.Number7);
        mTotalAmount7 = findViewById(R.id.total_amount_7);
        mProfitLosssAmount7 = findViewById(R.id.profitLosss_amount_7);
        mNumber8 = findViewById(R.id.Number8);
        mTotalAmount8 = findViewById(R.id.total_amount_8);
        mProfitLosssAmount8 = findViewById(R.id.profitLosss_amount_8);
        mNumber9 = findViewById(R.id.Number9);
        mTotalAmount9 = findViewById(R.id.total_amount_9);
        mProfitLosssAmount9 = findViewById(R.id.profitLosss_amount_9);
        mEtxResultNumber = findViewById(R.id.etxResultNumber);
        mDeclareResult = findViewById(R.id.declareResult);
        mProfitlossuser = findViewById(R.id.profitlossuser);
        mGName = findViewById(R.id.gName);
    }
}