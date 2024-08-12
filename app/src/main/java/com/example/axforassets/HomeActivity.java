package com.example.axforassets;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TextView textWelcome;
    private Handler sliderHandler = new Handler();
    private Runnable sliderRunnable;
    private TextView tabTerms, tabCondition;
    private FrameLayout tabContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager2 = findViewById(R.id.viewPager);
        textWelcome = findViewById(R.id.textWelcome);
        tabTerms = findViewById(R.id.tabTerms);
        tabCondition = findViewById(R.id.tabCondition);
        tabContent = findViewById(R.id.tabContent);

        // Assuming you want to display a dynamic username
        String username = getIntent().getStringExtra("EXTRA_USERNAME");
        textWelcome.setText("Welcome, " + username);

//        List<SlideItem> sliderItem = new ArrayList<>();
//        sliderItem.add(new SlideItem(R.drawable.c1));
//        sliderItem.add(new SlideItem(R.drawable.c2));
//        sliderItem.add(new SlideItem(R.drawable.c3));

        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.c1));
        slideItems.add(new SlideItem(R.drawable.c2));
        slideItems.add(new SlideItem(R.drawable.c3));


        SlideAdapter adapter = new SlideAdapter(slideItems, viewPager2);
        viewPager2.setAdapter(adapter);

        // Set the current item to a large number to start the infinite loop
        viewPager2.setCurrentItem(Integer.MAX_VALUE / 2, false);

        // Initialize the sliderRunnable
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager2.getCurrentItem();
                // Tambahkan satu ke currentItem tanpa perlu modulo
                int nextItem = currentItem + 1;
                viewPager2.setCurrentItem(nextItem, true); // Pindah satu per satu dengan smooth scroll
                sliderHandler.postDelayed(this, 2000); // Interval 3000 ms
            }
        };

        // Set up tab click listeners
        tabTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTermsContent();
            }
        });

        tabCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConditionContent();
            }
        });

        // Show the default tab content
        showTermsContent();
        onClickListenerNav();
    }

    private void showTermsContent() {
        System.out.println("showTermsContent dipanggil");

        // Ubah warna teks tab yang aktif
        tabTerms.setTextColor(Color.parseColor("#FFFF00")); // Kuning untuk tab aktif
        tabCondition.setTextColor(Color.parseColor("#FFFFFF")); // Putih untuk tab tidak aktif

        LayoutInflater inflater = LayoutInflater.from(this);
        View termsView = inflater.inflate(R.layout.terms_content, tabContent, false);

        if (termsView != null) {
            System.out.println("termsView berhasil di-inflate");
        } else {
            System.out.println("termsView gagal di-inflate");
        }

        tabContent.removeAllViews();
        tabContent.addView(termsView);
    }

    private void showConditionContent() {
        // Ubah warna teks tab yang aktif
        tabTerms.setTextColor(Color.parseColor("#FFFFFF")); // Putih untuk tab tidak aktif
        tabCondition.setTextColor(Color.parseColor("#FFFF00")); // Kuning untuk tab aktif

        LayoutInflater inflater = LayoutInflater.from(this);
        View conditionView = inflater.inflate(R.layout.condition_content, tabContent, false);
        tabContent.removeAllViews();
        tabContent.addView(conditionView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    protected void onClickListenerNav(){
        ImageView hamburgerMenu = findViewById(R.id.hamburgerMenu);
        ConstraintLayout nav = findViewById(R.id.nav);

        FrameLayout outsideNav = findViewById(R.id.backgroundFrame);

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

        itemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ItemsPageActivity.class);
//                intent.putExtra("EXTRA_USERNAME", username);
                startActivity(intent);
            }
        });

        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        logoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });
    }

}