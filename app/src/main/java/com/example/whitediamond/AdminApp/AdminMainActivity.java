package com.example.whitediamond.AdminApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.Adapter.GameAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.MainActivity;
import com.example.whitediamond.R;
import com.example.whitediamond.model.GamePojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMainActivity extends AppCompatActivity {

    private RecyclerView mAdminGameRecyle;
    private ProgressBar mAdminMainPb;

    private GridLayoutManager mLayoutManager;
    private GameAdapter mGameAdapter;
    private String tokken;
    private List<GamePojo> gamePojoList = new ArrayList<>();

    private boolean isAdmin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        tokken = prefs.getString("tokken", "no tokkens");

        mLayoutManager = new GridLayoutManager(AdminMainActivity.this, 2);
        mAdminGameRecyle.setLayoutManager(mLayoutManager);

        Call<List<GamePojo>> call = ApiClientInterface.getWDApiService().getGames(tokken);
        call.enqueue(new Callback<List<GamePojo>>() {
            @Override
            public void onResponse(Call<List<GamePojo>> call, Response<List<GamePojo>> response) {
                if (response.code() == 200) {
                    mAdminMainPb.setVisibility(View.GONE);
                    gamePojoList = response.body();
                    mGameAdapter = new GameAdapter(AdminMainActivity.this, gamePojoList , isAdmin);
                    mAdminGameRecyle.setAdapter(mGameAdapter);
                    mGameAdapter.notifyDataSetChanged();
                } else {
                    mAdminMainPb.setVisibility(View.GONE);
                    Toast.makeText(AdminMainActivity.this, "faild" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GamePojo>> call, Throwable t) {
                mAdminMainPb.setVisibility(View.GONE);
                Toast.makeText(AdminMainActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mAdminGameRecyle = findViewById(R.id.adminGameRecyle);
        mAdminMainPb = findViewById(R.id.adminMainPb);
    }
}