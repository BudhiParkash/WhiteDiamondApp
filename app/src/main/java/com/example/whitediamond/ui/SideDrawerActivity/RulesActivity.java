package com.example.whitediamond.ui.SideDrawerActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.R;

public class RulesActivity extends AppCompatActivity {

    private TextView mBckrules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        initView();
        mBckrules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mBckrules = findViewById(R.id.bckrules);
    }
}