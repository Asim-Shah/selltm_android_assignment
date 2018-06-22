package com.selltm.assignment.holiday.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.selltm.assignment.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Asim on 22/06/18.
 */
public class HolidaySwipeAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images;

    public HolidaySwipeAdapter(Context context) {
        this.context = context;
    }

    public void setData(int[] images) {
        this.images = images;
        notifyDataSetChanged();
    }

    /**
     * This method creates a view for the given position
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.holiday_swipe_layout, container, false);
        ImageView imageView = item_view.findViewById(R.id.holiday_images);
        Picasso.with(context).load(R.drawable.image2).into(imageView);
        container.addView(item_view);
        return item_view;
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by instantiateItem(ViewGroup, int)
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    /**
     * @return Returns the total count of images
     */
    @Override
    public int getCount() {
        int length = 0;
        if(null != images) {
            length = images.length;
        }
        return length;
    }

    /**
     * This method removes a page for the given position.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
