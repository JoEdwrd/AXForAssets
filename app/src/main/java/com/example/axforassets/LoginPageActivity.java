package com.example.axforassets;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button loginBtn = findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }
        });
    }

    protected void loginValidation() {
        // Find views
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        TextView usernameInputError = findViewById(R.id.usernameError);
        TextView passwordInputEmptyError = findViewById(R.id.passwordEmptyError);
        TextView passwordInputMinimumLengthError = findViewById(R.id.passwordMinimumLengthError);

        // Get input values
        String usernameVal = usernameInput.getText().toString().trim();
        String passwordVal = passwordInput.getText().toString().trim();

        // Initialize flags
        boolean isUsernameValid = !TextUtils.isEmpty(usernameVal);
        boolean isPasswordValid = !TextUtils.isEmpty(passwordVal) && passwordVal.length() >= 8;

        // Clear previous errors
        usernameInputError.setVisibility(View.INVISIBLE);
        passwordInputEmptyError.setVisibility(View.INVISIBLE);
        passwordInputMinimumLengthError.setVisibility(View.INVISIBLE);

        // Validate username
        if (!isUsernameValid) {
            usernameInput.setError("Username cannot be empty!");
            usernameInputError.setVisibility(View.VISIBLE);
        }

        // Validate password
        if (!isPasswordValid) {
            if (TextUtils.isEmpty(passwordVal)) {
                passwordInput.setError("Password cannot be empty!");
                passwordInputEmptyError.setVisibility(View.VISIBLE);
            } else {
                passwordInput.setError("Password must have minimum length of 8!");
                passwordInputMinimumLengthError.setVisibility(View.VISIBLE);
            }
        }

        // If both inputs are valid
        if (isUsernameValid && isPasswordValid) {
            Toast.makeText(LoginPageActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            UserData.getInstance().setUsername(usernameVal);
        }
    }

}