package com.example.whitediamond.SubAdmin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.LoginPojo;
import com.example.whitediamond.model.UserPojo;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private TextView mBckaddUser;
    private EditText mEtxUserID;
    private EditText mEtxUserPass;
    private EditText mEtxUserName;
    private TextInputLayout mTextInputEmail;
    private EditText mEtxaddPoints;
    private EditText mEtxRefferid;
    private Button mBtnCreateUser;

    private String tokken;
    private int refferid;
    private int totaldiamondPoint;

    private String userId, userPass, userName, userPoint, userReffer;
    private ProgressBar mAddProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initView();
        SharedPreferences prefs = getSharedPreferences("ProfileData", MODE_PRIVATE);
        try {
            totaldiamondPoint = prefs.getInt("dp", 0);

        } catch (Exception e) {

        }

        try {
            tokken = getIntent().getStringExtra("tokken");
            refferid = getIntent().getIntExtra("refferID", 0);
        } catch (Exception e) {

        }

        mBtnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = mEtxUserID.getText().toString();
                userPass = mEtxUserPass.getText().toString();
                userName = mEtxUserName.getText().toString();
                userPoint = mEtxaddPoints.getText().toString();
                userReffer = mEtxRefferid.getText().toString();

                if (TextUtils.isEmpty(userId)) {
                    mEtxUserID.setError("Please enter userId");
                    mEtxUserID.requestFocus();
                    return;
                } else if (!userId.contains("@gmail.com")) {
                    mEtxUserID.setError("Please enter validate email. Ex = test@gmail.com");
                    mEtxUserID.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(userPass)) {
                    mEtxUserPass.setError("Please enter Password");
                    mEtxUserPass.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(userName)) {
                    mEtxUserName.setError("Please enter user name");
                    mEtxUserName.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(userPoint)) {
                    mEtxaddPoints.setError("Minimum value is 10");
                    mEtxaddPoints.requestFocus();
                    return;
                } else if (userPoint.length() == 1) {
                    mEtxaddPoints.setError("Minimum value is 10");
                    mEtxaddPoints.requestFocus();
                    return;
                } else if (Integer.parseInt(userPoint) > totaldiamondPoint) {
                    mEtxaddPoints.setError("You have not sufficent balance");
                    mEtxaddPoints.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(userReffer)) {
                    mEtxRefferid.setError("Please enter reffer id");
                    mEtxRefferid.requestFocus();
                    return;
                } else if (!userReffer.equals(refferid + "")) {
                    mEtxRefferid.setError("Please enter correct reffer id");
                    mEtxRefferid.requestFocus();
                    return;
                } else {
                    mAddProgressBar.setVisibility(View.VISIBLE);
                    createUser(userId, userPass, userName, userPoint, userReffer);
                }


            }
        });

    }

    private void createUser(String userId, String userPass, String userName, String userPoint, String userReffer) {

        UserPojo userPojo = new UserPojo(userName,userId, userPass, Integer.parseInt(userPoint), Integer.parseInt(userReffer));

        Call<LoginPojo> call = ApiClientInterface.getWDApiService().createUser(tokken ,userPojo);
        call.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                if (response.code() == 201) {
                    mAddProgressBar.setVisibility(View.GONE);
                    Toast.makeText(AddUserActivity.this, "User Created sucessfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    mAddProgressBar.setVisibility(View.GONE);
                    Toast.makeText(AddUserActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
                mAddProgressBar.setVisibility(View.GONE);
                Toast.makeText(AddUserActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mBckaddUser = findViewById(R.id.bckaddUser);
        mEtxUserID = findViewById(R.id.etxUserID);
        mEtxUserPass = findViewById(R.id.etxUserPass);
        mEtxUserName = findViewById(R.id.etxUserName);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mEtxaddPoints = findViewById(R.id.etxaddPoints);
        mEtxRefferid = findViewById(R.id.etxRefferid);
        mBtnCreateUser = findViewById(R.id.btnCreateUser);
        mAddProgressBar = findViewById(R.id.addProgressBar);
    }
}