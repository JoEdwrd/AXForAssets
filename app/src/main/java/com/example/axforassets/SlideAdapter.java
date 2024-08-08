package com.example.axforassets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.slideViewHolder> {
    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;

    SlideAdapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public slideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new slideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull slideViewHolder holder, int position) {
        // Calculate the actual position in the slideItems list
        int actualPosition = position % slideItems.size();
        holder.setImage(slideItems.get(actualPosition));
    }

    @Override
    public int getItemCount() {
        // Return a very large number to support infinite scrolling
        return Integer.MAX_VALUE;
    }

    class slideViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        slideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SlideItem slideItem) {
            imageView.setImageResource(slideItem.getImage());

            // Set the ImageView size to match the parent size (ViewPager2)
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            imageView.setLayoutParams(layoutParams);
        }
    }
}