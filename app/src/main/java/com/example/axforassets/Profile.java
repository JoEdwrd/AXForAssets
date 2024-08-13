package com.example.axforassets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.axforassets.R;

public class Profile extends AppCompatActivity {

    String name = "username";
    String job = "Programmer";
    String email = "username@gmail.com";
    String phone = "0182618729171";
    String address = "Jl. Kenangan No. 1, Jakarta Barat";
    String username;
    TextView textWelcome;
    TextView textEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

//        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvJob = findViewById(R.id.tv_job);
//        TextView tvEmail = findViewById(R.id.tv_email);
        TextView tvPhone = findViewById(R.id.tv_phone);
        TextView tvAddress = findViewById(R.id.tv_address);
        ImageView ivProfile = findViewById(R.id.iv_profile);

//        tvUsername.setText(getString(R.string.greetings, name));
        tvJob.setText(getString(R.string.job, job));
//        tvEmail.setText(getString(R.string.email, email));
        tvPhone.setText(getString(R.string.phone, phone));
        tvAddress.setText(getString(R.string.address, address));
        username = getIntent().getStringExtra("EXTRA_USERNAME");
//        ivProfile.setImageResource(R.drawable.pexels_pixabay_220453);

        textWelcome = findViewById(R.id.tv_username);
        username = getIntent().getStringExtra("EXTRA_USERNAME");
        textWelcome.setText("Welcome, " + username);
        textEmail = findViewById(R.id.tv_email);
        textEmail.setText("Email : " +username+"@gmail.com");
        LinearLayout logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, LoginPageActivity.class);
                startActivity(intent);
                finish(); // Menutup aktivitas Profile sehingga pengguna tidak bisa kembali dengan tombol Back
            }
        });

        onClickListenerNav();
    }
    protected void onClickListenerNav(){
        ImageView hamburgerMenu = findViewById(R.id.hamburgerMenu);
        ConstraintLayout nav = findViewById(R.id.nav);

        FrameLayout outsideNav = findViewById(R.id.backgroundFrame);

        TextView homeNav = findViewById(R.id.homeNav);
        TextView itemNav = findViewById(R.id.itemNav);
        TextView profileNav = findViewById(R.id.profileNav);
        TextView logoutNav = findViewById(R.id.logoutNav);

        hamburgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.VISIBLE);
                outsideNav.setVisibility(View.VISIBLE);
            }
        });

        outsideNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.GONE);
                outsideNav.setVisibility(View.GONE);
            }
        });
        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, HomeActivity.class);
                intent.putExtra("EXTRA_USERNAME", username);
                startActivity(intent);
            }
        });
        itemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ItemsPageActivity.class);
                intent.putExtra("EXTRA_USERNAME", username);
                startActivity(intent);
            }
        });

        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, HomeActivity.class);
                intent.putExtra("EXTRA_USERNAME", username);
                startActivity(intent);
            }
        });

        logoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, LoginPageActivity.class);
                intent.putExtra("EXTRA_USERNAME", username);
                startActivity(intent);
            }
        });
    }


}