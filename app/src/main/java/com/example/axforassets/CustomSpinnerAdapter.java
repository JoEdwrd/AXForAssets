package com.example.axforassets;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CustomSpinnerAdapter extends ArrayAdapter<CharSequence> {

    private boolean isFirstTime = true;

    public CustomSpinnerAdapter(Context context, int resource, CharSequence[] objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return isFirstTime ? count : count - 1;
    }

    @Override
    public CharSequence getItem(int position) {
        return isFirstTime ? super.getItem(position) : super.getItem(position + 1);
    }

    public void setFirstTime(boolean firstTime) {
        this.isFirstTime = firstTime;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }
}
