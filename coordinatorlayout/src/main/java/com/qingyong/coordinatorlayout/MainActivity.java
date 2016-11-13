package com.qingyong.coordinatorlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(mToolbar);
    }

    public void toolbarUse(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AppbarToolbarActivity.class);
        startActivity(intent);
    }

    public void collToolbarUse(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AppbarColltoolbarActivity.class);
        startActivity(intent);
    }

}
