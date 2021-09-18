package com.example.whitediamond.ui.SideDrawerActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whitediamond.Api.ApiClientInterface;
import com.example.whitediamond.R;
import com.example.whitediamond.model.ChangePasswordPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private String tokken;
    private TextView mBckChangeP;
    private EditText mEditTextCurrentPassword;
    private EditText mEtxnewPassword;
    private EditText mEtxConfirmPassword;
    private Button mBtnChangeP;
    private String currentPass;
    private String newPass;
    private String confirmPass;
    private ProgressBar mChangeProgessbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        try {
            tokken = getIntent().getStringExtra("token");
        } catch (Exception e) {

        }

        mBckChangeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBtnChangeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPass = mEditTextCurrentPassword.getText().toString();
                newPass = mEtxnewPassword.getText().toString();
                confirmPass = mEtxConfirmPassword.getText().toString();

                if (currentPass.isEmpty()) {
                    mEditTextCurrentPassword.setError("Please fill current password");
                    mEditTextCurrentPassword.requestFocus();
                    return;
                } else if (newPass.isEmpty()) {
                    mEtxnewPassword.setError("Please fill new password");
                    mEtxnewPassword.requestFocus();
                    return;
                } else if (confirmPass.isEmpty()) {
                    mEtxConfirmPassword.setError("Please fill confirm password");
                    mEtxConfirmPassword.requestFocus();
                    return;
                } else if (!newPass.equals(confirmPass)) {
                    mEtxConfirmPassword.setError("Confirm password is not matched with new password");
                    mEtxConfirmPassword.requestFocus();
                    return;
                } else {

                    changePassord(currentPass, newPass);

                }
            }
        });


    }

    private void changePassord(String currentPass, String newPass) {
        mChangeProgessbar.setVisibility(View.VISIBLE);
        ChangePasswordPojo changePasswordPojo = new ChangePasswordPojo(currentPass, newPass);
        Call<Void> call = ApiClientInterface.getWDApiService().changePass(tokken, changePasswordPojo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    mChangeProgessbar.setVisibility(View.GONE);
                    Toast.makeText(ChangePasswordActivity.this, "Password change successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    mChangeProgessbar.setVisibility(View.GONE);
                    Toast.makeText(ChangePasswordActivity.this, "Password change failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mChangeProgessbar.setVisibility(View.GONE);
                Toast.makeText(ChangePasswordActivity.this, "try after sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mBckChangeP = findViewById(R.id.bckChangeP);
        mEditTextCurrentPassword = findViewById(R.id.editTextCurrentPassword);
        mEtxnewPassword = findViewById(R.id.etxnewPassword);
        mEtxConfirmPassword = findViewById(R.id.etxConfirmPassword);
        mBtnChangeP = findViewById(R.id.btnChangeP);
        mChangeProgessbar = findViewById(R.id.changeProgessbar);
    }
}