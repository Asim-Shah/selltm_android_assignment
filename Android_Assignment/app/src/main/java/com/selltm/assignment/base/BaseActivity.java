package com.selltm.assignment.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.selltm.assignment.R;

/**
 * Created by Asim on 20/06/18.
 */
public class BaseActivity extends AppCompatActivity {

    private TextView mToolbarTitle;
    private ImageButton navigationDrawerButton, backButton, shareButton;
    private Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View toolBarLayout = inflater.inflate(R.layout.activity_toolbar_base, null);
        setSupportActionBar((Toolbar) toolBarLayout.findViewById(R.id.toolbar));
        if(null != getSupportActionBar())
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        inflater.inflate(layoutResID, (ViewGroup) toolBarLayout.findViewById(R.id.view_container), true);
        super.setContentView(toolBarLayout);
        init();
    }

    private void init() {
        setUpToolbar();
    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        hideActionBar();
    }

    public void setHomeToolbar() {
        showActionBar();
        View mCustomView = getLayoutInflater().inflate(R.layout.action_bar_home, null);
        toolbar.removeAllViews();
        /*toolbar.addView(mCustomView, new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));*/
        toolbar.addView(mCustomView, new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        if(null != getSupportActionBar()) {
            getSupportActionBar().setElevation(0);
            mToolbarTitle = mCustomView.findViewById(R.id.title);
            navigationDrawerButton = mCustomView.findViewById(R.id.navigation_drawer_button);
            if(null != navigationDrawerButton)
                navigationDrawerButton.setOnClickListener(onClickListener);
            backButton = mCustomView.findViewById(R.id.back_button);
            if(null != backButton)
                backButton.setOnClickListener(onClickListener);
        }
    }

    //TODO: Resize back icon from 32 to 28. Check in screen3 jpg
    public void setToolbar() {
        showActionBar();
        View mCustomView = getLayoutInflater().inflate(R.layout.action_bar_common, null);
        toolbar.removeAllViews();
        toolbar.addView(mCustomView, new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        if(null != getSupportActionBar()) {
            getSupportActionBar().setElevation(0);
            mToolbarTitle = mCustomView.findViewById(R.id.title);
            /*navigationDrawerButton = mCustomView.findViewById(R.id.navigation_drawer_button);
            if(null != navigationDrawerButton)
                navigationDrawerButton.setOnClickListener(onClickListener);*/
            backButton = mCustomView.findViewById(R.id.back_button);
            if(null != backButton)
                backButton.setOnClickListener(onClickListener);
        }
    }

    public void setHolidayToolbar() {
        showActionBar();
        View mCustomView = getLayoutInflater().inflate(R.layout.action_bar_holiday, null);
        toolbar.removeAllViews();
        toolbar.addView(mCustomView, new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        if(null != getSupportActionBar()) {
            getSupportActionBar().setElevation(0);
            mToolbarTitle = mCustomView.findViewById(R.id.title);
            /*navigationDrawerButton = mCustomView.findViewById(R.id.navigation_drawer_button);
            if(null != navigationDrawerButton)
                navigationDrawerButton.setOnClickListener(onClickListener);*/
            backButton = mCustomView.findViewById(R.id.back_button);
            if(null != backButton)
                backButton.setOnClickListener(onClickListener);
        }
    }

    public void setToolbarShare() {
        showActionBar();
        View mCustomView = getLayoutInflater().inflate(R.layout.action_bar_share, null);
        toolbar.removeAllViews();
        toolbar.addView(mCustomView, new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        if(null != getSupportActionBar()) {
            getSupportActionBar().setElevation(0);
            mToolbarTitle = mCustomView.findViewById(R.id.title);
            shareButton = mCustomView.findViewById(R.id.share_button);
            if(null != shareButton)
                shareButton.setOnClickListener(onClickListener);
            backButton = mCustomView.findViewById(R.id.back_button);
            if(null != backButton)
                backButton.setOnClickListener(onClickListener);
        }
    }

    public void setToolbarTitle(String title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navigation_drawer_button:
                    openNavigationDrawer();
                    break;
                case R.id.back_button:
                    onBackClicked();
                    break;
                case R.id.share_button:
                    onShareClicked();
                    break;
                default:
                    break;
            }
        }
    };

    private void onShareClicked() {
        Toast.makeText(BaseActivity.this, "Share clicked", Toast.LENGTH_SHORT).show();
    }

    public void onBackClicked() {
        super.onBackPressed();
    }

    private void openNavigationDrawer() {
        Toast.makeText(BaseActivity.this, "Open Navigation Drawer", Toast.LENGTH_SHORT).show();
    }

    private void showActionBar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    public void hideActionBar() {
        toolbar.setVisibility(View.GONE);
    }

}
