package com.example.axforassets;

import android.widget.ImageView;

import java.util.Date;

public class ItemDetailData {

    private static ItemDetailData instance;

    public String desc;
    public String title;
    public String price;
    public String longDesc;
    public String category;
    public String releaseDate;

    public static synchronized ItemDetailData getInstance() {
        if (instance == null) {
            instance = new ItemDetailData();
        }
        return instance;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}
