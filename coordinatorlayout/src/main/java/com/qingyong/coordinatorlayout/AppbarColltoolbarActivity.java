package com.qingyong.coordinatorlayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class AppbarColltoolbarActivity extends AppCompatActivity {

    String TAG = "[" + AppbarColltoolbarActivity.class.getSimpleName() + "]";

    AppBarLayout appbarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    LinearLayout toolbarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_colltoolbar);

        appbarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coll_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarMenu = (LinearLayout) findViewById(R.id.toolbar_menu);

        setSupportActionBar(toolbar);

        PaletteUtil.paletteGen(this, R.drawable.bing_wallpaper, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                try {
                    int transColor = getResources().getColor(android.R.color.transparent);
                    int defaultColor = getResources().getColor(R.color.colorPrimary);
                    int defaultTitleColor = Color.WHITE;
                    int bgColor = palette.getDarkVibrantColor(defaultColor);
                    int titleColor = palette.getLightVibrantColor(defaultTitleColor);

                    bgColor = transColor;

                    collapsingToolbarLayout.setContentScrimColor(bgColor);
                    collapsingToolbarLayout.setCollapsedTitleTextColor(titleColor);
                    collapsingToolbarLayout.setExpandedTitleColor(titleColor);
                } catch (Exception ignored) {
                }
            }
        });

        appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.i(TAG + "[STATE]", "status : " + state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbarMenu.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle(" ");
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbarMenu.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("说走就走");
                } else {
                    //中间状态
                    toolbarMenu.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle(" ");
                }
            }
        });


    }
}
