package com.example.axforassets;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.axforassets.R;

public class Profile extends AppCompatActivity {

    String name = "username";
    String job = "Programmer";
    String email = "username@gmail.com";
    String phone = "0182618729171";
    String address = "Jl. Kenangan No. 1, Jakarta Barat";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvJob = findViewById(R.id.tv_job);
        TextView tvEmail = findViewById(R.id.tv_email);
        TextView tvPhone = findViewById(R.id.tv_phone);
        TextView tvAddress = findViewById(R.id.tv_address);
        ImageView ivProfile = findViewById(R.id.iv_profile);

        tvUsername.setText(getString(R.string.greetings, name));
        tvJob.setText(getString(R.string.job, job));
        tvEmail.setText(getString(R.string.email, email));
        tvPhone.setText(getString(R.string.phone, phone));
        tvAddress.setText(getString(R.string.address, address));
//        ivProfile.setImageResource(R.drawable.pexels_pixabay_220453);
    }
}