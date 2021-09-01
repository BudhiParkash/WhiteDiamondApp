package com.example.whitediamond.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.MainActivity;
import com.example.whitediamond.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private TextInputLayout mTextInputEmail;
    private EditText mEditTextEmail;
    private TextInputLayout mTextInputPassword;
    private EditText mEditTextPassword;
    private Button mCirLoginButton;
    private ImageView mDaimondlogo;
    private Animation diamondAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        diamondAnimation = AnimationUtils.loadAnimation(this , R.anim.diamonanimation);

        mDaimondlogo.setAnimation(diamondAnimation);

        mCirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
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
    }
}