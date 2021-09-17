package com.example.whitediamond.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitediamond.Adapter.NumberAdapter;
import com.example.whitediamond.MainActivity;
import com.example.whitediamond.R;
import com.example.whitediamond.model.NumberPojo;

import java.util.ArrayList;
import java.util.List;

public class SelectNumberActivity extends AppCompatActivity {

    private String gameName;
    private TextView mGameName;
    private NumberAdapter mNumberAdapter;
    private List<NumberPojo> numberPojoList = new ArrayList<>();
    private TextView mBcknumber;
    private RecyclerView mNumberRecycle;
    private GridLayoutManager mLayoutManager;
    private String resulttime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        initView();


        mLayoutManager = new GridLayoutManager(SelectNumberActivity.this, 3);
        mNumberRecycle.setLayoutManager(mLayoutManager);

        try {

        }
        catch (Exception e){
            resulttime = getIntent().getStringExtra("");
            gameName = getIntent().getStringExtra("gameName");
        }

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

        mNumberAdapter = new NumberAdapter(SelectNumberActivity.this , numberPojoList , gameName);
        mNumberRecycle.setAdapter(mNumberAdapter);
        mNumberAdapter.notifyDataSetChanged();





       /* mDiamond9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectNumberActivity.this , MoneyActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void initView() {
        mGameName = findViewById(R.id.gameName);
        mBcknumber = findViewById(R.id.bcknumber);
        mNumberRecycle = findViewById(R.id.numberRecycle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}