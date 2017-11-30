package com.example.shuangzhecheng.loweskevinapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuangzhecheng.loweskevinapp.network.RetrofitClient;
import com.example.shuangzhecheng.loweskevinapp.network.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {


    @BindView(R.id.buttonRegister)
    Button buttonRegister;
    @BindView(R.id.editTextRname)
    EditText name;
    @BindView(R.id.editTextRemail)
    EditText email;
    @BindView(R.id.editTextRmobile)
    EditText mobile;
    @BindView(R.id.editTextRpassword)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        ButterKnife.bind(this);


    }

    private void doRegister(String name, String email, String mobile, String password) {
        UserService apiInterface = RetrofitClient.getClient().create(UserService.class);
        Call<String> call = apiInterface.getRegisterResponse(name, email, mobile, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(RegisterPage.this, "RESPONSE : " + response.body(), Toast.LENGTH_SHORT).show();
                String valid = response.body();
                if(response.body().equals(valid)) {
                    Intent myIntent = new Intent(RegisterPage.this, MainActivity.class);
                    RegisterPage.this.startActivity(myIntent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterPage.this, "RESPONSE IS FAILED", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.buttonRegister)
    public void onViewClicked() {
        String nm = name.getText().toString();
        String em = email.getText().toString();
        String mb = mobile.getText().toString();
        String pw = password.getText().toString();
        doRegister(nm, em, mb, pw);
    }
}
