package com.example.whitediamond;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
import com.example.whitediamond.model.GamePojo;
import com.example.whitediamond.ui.SideDrawerActivity.ChangePasswordActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private TextView mMenuHome;
    private RelativeLayout mAccountStatementLayout;
    private RelativeLayout mBethistoryLayout;
    private RelativeLayout mUnsetteledbetLayout;
    private RelativeLayout mChangePLayout;
    private RelativeLayout mRulesLayout;
    private RelativeLayout mTermConditionL;
    private RecyclerView mGameRecyle;

    private GridLayoutManager mLayoutManager;
    private GameAdapter mGameAdapter;
    private String tokken;
    private List<GamePojo> gamePojoList = new ArrayList<>();
    private ProgressBar mMainProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        tokken = prefs.getString("tokken", "no tokkens");
        mMainProgressbar.setVisibility(View.VISIBLE);

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
                    mGameAdapter = new GameAdapter(MainActivity.this, gamePojoList);
                    mGameRecyle.setAdapter(mGameAdapter);
                    mGameAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
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
                startActivity(intent);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mMenuHome = findViewById(R.id.menu_home);
        mAccountStatementLayout = findViewById(R.id.accountStatement_Layout);
        mBethistoryLayout = findViewById(R.id.bethistory_layout);
        mUnsetteledbetLayout = findViewById(R.id.unsetteledbet_layout);
        mChangePLayout = findViewById(R.id.changeP_layout);
        mRulesLayout = findViewById(R.id.rules_layout);
        mTermConditionL = findViewById(R.id.term_conditionL);
        mGameRecyle = findViewById(R.id.gameRecyle);
        mMainProgressbar = findViewById(R.id.mainProgressbar);
    }
}