package com.example.customertextview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customertextview.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRv = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
        for (char i = 'a'; i < 'z'; i++) {
            mList.add(i + "");
        }
        TestAdapter adapter = new TestAdapter(mList, this);
        mRv.setAdapter(adapter);
    }
}
