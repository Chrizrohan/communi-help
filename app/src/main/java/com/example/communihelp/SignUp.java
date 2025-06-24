package com.example.communihelp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.databinding.ActivitySignUpBinding;
import com.example.communihelp.server.SignupResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;

    String userName,email,phoneNo,pass,conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.sign.setOnClickListener(v -> {
            if(isValidData()){
                registerUser(userName,email,phoneNo,pass);
            }
        });
    }

    private boolean isValidData() {

        boolean isValid = true;

        userName = binding.name.getText().toString();
        email = binding.email.getText().toString();
        phoneNo = binding.phonenumber.getText().toString();
        pass = binding.password.getText().toString();
        conPass = binding.confirmpass.getText().toString();

        if (userName.isEmpty()){
            binding.name.setError("User Name can't be empty");
            isValid = false;
        }
        if (email.isEmpty()){
            binding.email.setError("Email can't be empty");
            isValid = false;
        }
        if (phoneNo.isEmpty()){
            binding.phonenumber.setError("Phone Number can't be empty");
            isValid = false;
        }

        if (phoneNo.length()<10){
            binding.phonenumber.setError("Entered valid Phone Number ");
            isValid = false;
        }
        if (pass.isEmpty()){
            binding.password.setError("password can't be empty");
            isValid = false;
        }
        if (conPass.isEmpty()){
            binding.confirmpass.setError("Confirm Password can't be empty");
            isValid = false;
        }

        if (!pass.equals(conPass)){
            binding.confirmpass.setError("Confirm password doesn't match");
            isValid = false;
        }

        return isValid;
    }

    private void registerUser(String userName, String email, String phoneNo, String pass) {
        ApiService service = ApiClient.getClient().create(ApiService.class);

        service.signup(userName,email,phoneNo,pass).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {

                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(SignUp.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

                Toast.makeText(SignUp.this, ""+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
