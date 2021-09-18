package com.example.whitediamond.ui.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.AdminApp.AdminMainActivity;
import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.MainActivity;
import com.example.whitediamond.R;
import com.example.whitediamond.model.LoginPojo;
import com.example.whitediamond.model.UserPojo;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextInputLayout mTextInputEmail;
    private EditText mEditTextEmail;
    private TextInputLayout mTextInputPassword;
    private EditText mEditTextPassword;
    private Button mCirLoginButton;
    private ImageView mDaimondlogo;
    private Animation diamondAnimation;
    private String tokken;
    private String userId;
    private ProgressBar mLoginProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        diamondAnimation = AnimationUtils.loadAnimation(this, R.anim.diamonanimation);

        mDaimondlogo.setAnimation(diamondAnimation);

        mCirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEditTextEmail.getText().toString();
                String pass = mEditTextPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    mEditTextEmail.setError("Please Enter Email");
                    mEditTextEmail.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(pass)) {
                    mEditTextPassword.setError("Please Enter Password");
                    mEditTextPassword.requestFocus();
                    return;
                }
                else if(username.equals("superadmin@gmail.com") || pass.equals("super@123")){
                    mLoginProgressbar.setVisibility(View.VISIBLE);
                    UserPojo newLogin = new UserPojo(username, pass);
                    Call<LoginPojo> logins = ApiClientInterface.getWDApiService().login(newLogin);

                    logins.enqueue(new Callback<LoginPojo>() {
                        @Override
                        public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                            if (response.code() == 200) {
                                mLoginProgressbar.setVisibility(View.GONE);
                                LoginPojo loginObject = response.body();
                                tokken = loginObject.getTokens();
                                userId = loginObject.getUser().getId();
                                SharedPreferences.Editor editor = getSharedPreferences("ProfileData", MODE_PRIVATE).edit();
                                editor.putString("tokken", tokken);
                                editor.putString("userId" , userId);
                                editor.putBoolean("superAdmin" , true);
                                editor.apply();
                                Intent intent = new Intent(Login.this, AdminMainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                mLoginProgressbar.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Email or Password is wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginPojo> call, Throwable t) {
                            Toast.makeText(Login.this, "Error-LOG2", Toast.LENGTH_SHORT).show();
                            mLoginProgressbar.setVisibility(View.GONE);
                        }
                    });

                }
                else {

                    goEmailLogin(username, pass);
                }
            }
        });
    }

    private void goEmailLogin(String username, String pass) {
        mLoginProgressbar.setVisibility(View.VISIBLE);
        UserPojo newLogin = new UserPojo(username, pass);
        Call<LoginPojo> logins = ApiClientInterface.getWDApiService().login(newLogin);

        logins.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                if (response.code() == 200) {
                    mLoginProgressbar.setVisibility(View.GONE);
                    LoginPojo loginObject = response.body();
                    tokken = loginObject.getTokens();
                    userId = loginObject.getUser().getId();
                    SharedPreferences.Editor editor = getSharedPreferences("ProfileData", MODE_PRIVATE).edit();
                    editor.putString("tokken", tokken);
                    editor.putString("userId" , userId);
                    editor.apply();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    mLoginProgressbar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Email or Password is wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
                Toast.makeText(Login.this, "Error-LOG2", Toast.LENGTH_SHORT).show();
                mLoginProgressbar.setVisibility(View.GONE);
            }
        });

    }

    private void initView() {
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mCirLoginButton = findViewById(R.id.cirLoginButton);
        mDaimondlogo = findViewById(R.id.daimondlogo);
        mLoginProgressbar = findViewById(R.id.loginProgressbar);
    }
}