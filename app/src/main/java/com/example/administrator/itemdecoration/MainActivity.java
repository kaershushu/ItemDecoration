package com.example.administrator.itemdecoration;

import com.example.administrator.itemdecoration.itemdecoration.NormalDecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.addItemDecoration(new NormalDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new Tadapter(this));
        ((RadioGroup) findViewById(R.id.rb_control)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        mRecyclerView.addItemDecoration(new NormalDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case R.id.rb_2:
                        mRecyclerView.addItemDecoration(new NormalDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL));
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
