package com.example.whitediamond.SubAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.Adapter.ResultAdapter;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.UserPojo;
import com.example.whitediamond.ui.Activity.SelectNumberActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubAdminActivity extends AppCompatActivity {


    private String tokken;
    private int refferid, refferby;
    private TextView mBcksubadmin;
    private FloatingActionButton mAddUser;
    private RecyclerView mSubUserReccyle;
    private ProgressBar mSubadminProgressBar;

    private SubAdminUserAdapter subAdminUserAdapter;
    private List<UserPojo> userPojoList = new ArrayList<>();
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_admin);
        initView();

        try {
            tokken = getIntent().getStringExtra("tokken");
            refferid = getIntent().getIntExtra("refferID", 0);
            refferby = getIntent().getIntExtra("refferBy", 0);
        } catch (Exception e) {

        }

        manager = new LinearLayoutManager(this);


        mSubUserReccyle.setHasFixedSize(true);
        mSubUserReccyle.setLayoutManager(manager);


        mBcksubadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubAdminActivity.this, AddUserActivity.class);
                intent.putExtra("tokken", tokken);
                intent.putExtra("refferID", refferid);
                startActivity(intent);
            }
        });

        mSubadminProgressBar.setVisibility(View.VISIBLE);
        Call<List<UserPojo>> call = ApiClientInterface.getWDApiService().getreferList(tokken);
        call.enqueue(new Callback<List<UserPojo>>() {
            @Override
            public void onResponse(Call<List<UserPojo>> call, Response<List<UserPojo>> response) {
                if(response.code() == 200){
                    userPojoList = response.body();
                    subAdminUserAdapter = new SubAdminUserAdapter(SubAdminActivity.this, userPojoList);
                    mSubUserReccyle.setAdapter(subAdminUserAdapter);
                    subAdminUserAdapter.notifyDataSetChanged();
                    mSubadminProgressBar.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(SubAdminActivity.this, "No User", Toast.LENGTH_SHORT).show();
                    mSubadminProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<UserPojo>> call, Throwable t) {
                mSubadminProgressBar.setVisibility(View.GONE);
                Toast.makeText(SubAdminActivity.this, "try after some times", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initView() {
        mBcksubadmin = findViewById(R.id.bcksubadmin);
        mAddUser = findViewById(R.id.addUser);
        mSubUserReccyle = findViewById(R.id.subUserReccyle);
        mSubadminProgressBar = findViewById(R.id.subadminProgressBar);
    }
}