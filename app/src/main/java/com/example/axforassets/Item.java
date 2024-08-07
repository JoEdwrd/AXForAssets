package com.example.axforassets;

import android.graphics.drawable.Drawable;

public class Item {
    private String title;
    private String category;
    private String desc;
    private String longDesc;
    private String price;
    private String releaseDate;

    private String img;

    public Item(String title, String category, String desc, String longDesc, String price, String releaseDate) {
        this.title = title;
        this.category = category;
        this.desc = desc;
        this.longDesc = longDesc;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDesc() {
        return desc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public String getPrice() {
        return price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
