package com.example.administrator.itemdecoration;

import com.example.administrator.itemdecoration.itemdecoration.OverlayDecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    private OverlayDecoration mNormalDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.recycler);
        mNormalDecoration = new OverlayDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mNormalDecoration);
        mRecyclerView.setAdapter(new Tadapter(this));
        ((RadioGroup) findViewById(R.id.rb_control)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mNormalDecoration != null){
                    mRecyclerView.removeItemDecoration(mNormalDecoration);
                }
                switch (checkedId) {
                    case R.id.rb_1:
                        //重新添加ItemDecoration必须先移除旧的，防止对新ItemDecoration产生影响
                        mNormalDecoration = new OverlayDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                        mRecyclerView.addItemDecoration(mNormalDecoration);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case R.id.rb_2:
                        mNormalDecoration = new OverlayDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL);
                        mRecyclerView.addItemDecoration(mNormalDecoration);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
