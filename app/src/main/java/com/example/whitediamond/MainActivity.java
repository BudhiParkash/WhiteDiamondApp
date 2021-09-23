package com.example.whitediamond;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.Adapter.GameAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.SubAdmin.SubAdminActivity;
import com.example.whitediamond.model.GamePojo;
import com.example.whitediamond.model.UserPojo;
import com.example.whitediamond.ui.Activity.Login;
import com.example.whitediamond.ui.SideDrawerActivity.BetHistoryActivity;
import com.example.whitediamond.ui.SideDrawerActivity.ChangePasswordActivity;
import com.example.whitediamond.ui.SideDrawerActivity.RulesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private TextView mMenuHome;
    private TextView mBethistoryLayout;

    private RelativeLayout mChangePLayout;
    private RelativeLayout mRulesLayout;
    private RelativeLayout mLogout;
    private RecyclerView mGameRecyle;

    private GridLayoutManager mLayoutManager;
    private GameAdapter mGameAdapter;
    private String tokken;
    private List<GamePojo> gamePojoList = new ArrayList<>();
    private ProgressBar mMainProgressbar;
    private LinearLayout mDrawerLinerLayout;
    private int diamonPoints;
    private TextView mDiamondpoints;
    private TextView mUserName;
    private TextView mAccountUserName;
    private String userId;
    private RelativeLayout mSubAdminLayout;

    private boolean isSabAdmin;
    private int refferID;
    private int refferBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        tokken = prefs.getString("tokken", "no tokkens");
        mMainProgressbar.setVisibility(View.VISIBLE);
        mDrawerLinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getUser();

        mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mGameRecyle.setLayoutManager(mLayoutManager);

       /* mLakhmilayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectNumberActivity.class);
                startActivity(intent);
            }
        });*/


        Call<List<GamePojo>> call = ApiClientInterface.getWDApiService().getGames(tokken);
        call.enqueue(new Callback<List<GamePojo>>() {
            @Override
            public void onResponse(Call<List<GamePojo>> call, Response<List<GamePojo>> response) {
                if (response.code() == 200) {
                    mMainProgressbar.setVisibility(View.GONE);
                    gamePojoList = response.body();
                    mGameAdapter = new GameAdapter(MainActivity.this, gamePojoList, false);
                    mGameRecyle.setAdapter(mGameAdapter);
                    mGameAdapter.notifyDataSetChanged();
                } else {
                    mMainProgressbar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "faild" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GamePojo>> call, Throwable t) {
                mMainProgressbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
            }
        });


        mMenuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mChangePLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                intent.putExtra("token", tokken);
                startActivity(intent);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });




        mSubAdminLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , SubAdminActivity.class);
                intent.putExtra("tokken" , tokken);
                intent.putExtra("refferID" , refferID);
                intent.putExtra("refferBy", refferBy);
                startActivity(intent);
                mDrawerLayout.closeDrawer(Gravity.LEFT);

            }
        });

    }

    private void getUser() {

        Call<UserPojo> call = ApiClientInterface.getWDApiService().getUser(tokken);
        call.enqueue(new Callback<UserPojo>() {
            @Override
            public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {
                if (response.code() == 200) {
                    UserPojo data = response.body();
                    diamonPoints = data.getDimondPoint();
                    isSabAdmin = data.getSubAdmin();
                    refferID = data.getRefferId();
                    refferBy = data.getRefferBy();
                    userId = data.getId();
                    SharedPreferences.Editor editor = getSharedPreferences("ProfileData", MODE_PRIVATE).edit();
                    editor.putInt("dp", diamonPoints);
                    editor.apply();
                    mDiamondpoints.setText(diamonPoints + "");
                    mAccountUserName.setText(data.getUser_name());
                    mUserName.setText("Hi! " + data.getUser_name());
                    mMainProgressbar.setVisibility(View.GONE);
                    if(isSabAdmin){
                        mSubAdminLayout.setVisibility(View.VISIBLE);
                    }

                } else {

                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    mMainProgressbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UserPojo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
                mMainProgressbar.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mMenuHome = findViewById(R.id.menu_home);
        mBethistoryLayout = findViewById(R.id.betHistory);
        mChangePLayout = findViewById(R.id.changeP_layout);
        mRulesLayout = findViewById(R.id.rules_layout);
        mLogout = findViewById(R.id.logout_layout);
        mGameRecyle = findViewById(R.id.gameRecyle);
        mMainProgressbar = findViewById(R.id.mainProgressbar);
        mDrawerLinerLayout = findViewById(R.id.drawer_linerLayout);

        mDiamondpoints = findViewById(R.id.diamondpoints);
        mUserName = findViewById(R.id.userName);
        mAccountUserName = findViewById(R.id.accountUserName);


        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("ProfileData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();

            }
        });


        mBethistoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BetHistoryActivity.class);
                intent.putExtra("token", tokken);
                intent.putExtra("userid", userId);
                startActivity(intent);

            }
        });

        mRulesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });


        mSubAdminLayout = findViewById(R.id.subAdmin_layout);
    }
}