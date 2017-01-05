package com.yong.customerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void basePrace(View view) {
        Intent intent = new Intent(this, CusViewBaseActivity.class);
        startActivity(intent);
    }

}
