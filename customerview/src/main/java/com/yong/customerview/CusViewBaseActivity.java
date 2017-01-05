package com.yong.customerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 基础
 */
public class CusViewBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        View.MeasureSpec measureSpec = new View.MeasureSpec();
    }
}
