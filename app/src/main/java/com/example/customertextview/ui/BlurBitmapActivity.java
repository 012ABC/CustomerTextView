package com.example.customertextview.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.customertextview.MainActivity;
import com.example.customertextview.R;
import com.example.customertextview.view.ZProgressBar;

/**
 * @author dylan
 */
public class BlurBitmapActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtn;
    private AppCompatEditText mEdit;
    private ZProgressBar mProgressBar;
    private String ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_bitmap);
        initView();

    }

    /**
     * 初始化布局控件
     */
    private void initView() {
        mBtn = findViewById(R.id.btn);
        mEdit = findViewById(R.id.edit);
       /* mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setLeftColor("#ff3333");
        mProgressBar.setRightColor("#660033");
        mProgressBar.setLeftTitle("肯定");
        mProgressBar.setRightTitle("否定");
        mProgressBar.setLeftRatio("50%");
        mProgressBar.setRightRatio("50%");
        mProgressBar.setRatio(0.5f);*/
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn) {
            startActivity(new Intent(BlurBitmapActivity.this, MainActivity.class));
        }
    }
}
