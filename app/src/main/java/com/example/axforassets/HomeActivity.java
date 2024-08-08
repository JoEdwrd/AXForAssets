package com.example.axforassets;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
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
        String username = "Joseph";
        textWelcome.setText("Welcome, " + username);

        List<SlideItem> sliderItem = new ArrayList<>();
        sliderItem.add(new SlideItem(R.drawable.c1));
        sliderItem.add(new SlideItem(R.drawable.c2));
        sliderItem.add(new SlideItem(R.drawable.c3));

        SlideAdapter adapter = new SlideAdapter(sliderItem, viewPager2);
        viewPager2.setAdapter(adapter);

        // Set the current item to a large number to start the infinite loop
        viewPager2.setCurrentItem(Integer.MAX_VALUE / 2, false);

        // Initialize the sliderRunnable
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(currentItem + 1, true);
                sliderHandler.postDelayed(this, 3000);
            }
        };
        sliderHandler.postDelayed(sliderRunnable, 3000);

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
    }

    private void showTermsContent() {
        // Ubah warna teks tab yang aktif
        tabTerms.setTextColor(Color.parseColor("#FFFF00")); // Kuning untuk tab aktif
        tabCondition.setTextColor(Color.parseColor("#FFFFFF")); // Putih untuk tab tidak aktif

        // Terapkan border bottom pada tab aktif
//        tabTerms.setBackgroundResource(R.drawable.textview_border_bottom);
//        tabCondition.setBackground(null); // Hapus border pada tab yang tidak aktif

        LayoutInflater inflater = LayoutInflater.from(this);
        View termsView = inflater.inflate(R.layout.terms_content, tabContent, false);
        tabContent.removeAllViews();
        tabContent.addView(termsView);
    }

    private void showConditionContent() {
        // Ubah warna teks tab yang aktif
        tabTerms.setTextColor(Color.parseColor("#FFFFFF")); // Putih untuk tab tidak aktif
        tabCondition.setTextColor(Color.parseColor("#FFFF00")); // Kuning untuk tab aktif

        // Terapkan border bottom pada tab aktif
//        tabCondition.setBackgroundResource(R.drawable.textview_border_bottom);
//        tabTerms.setBackground(null); // Hapus border pada tab yang tidak aktif

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
}