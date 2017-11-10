package com.example.administrator.itemdecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.administrator.itemdecoration.adapter.Tadapter;
import com.example.administrator.itemdecoration.adapter.TimeLineAdapter;
import com.example.administrator.itemdecoration.itemdecoration.NormalDecoration;
import com.example.administrator.itemdecoration.itemdecoration.TimeLineDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.ItemDecoration mDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mRecyclerView;
        mRecyclerView = findViewById(R.id.recycler);
        mDecoration = new NormalDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(new Tadapter(MainActivity.this));
        ((RadioGroup) findViewById(R.id.rb_control)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mDecoration != null) {
                    mRecyclerView.removeItemDecoration(mDecoration);
                }
                switch (checkedId) {
                    case R.id.rb_1:
                        //重新添加ItemDecoration必须先移除旧的，防止对新ItemDecoration产生影响
                        mDecoration = new NormalDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                        mRecyclerView.addItemDecoration(mDecoration);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        mRecyclerView.setAdapter(new Tadapter(MainActivity.this));
                        break;
                    case R.id.rb_2:
                        mDecoration = new NormalDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL);
                        mRecyclerView.addItemDecoration(mDecoration);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        mRecyclerView.setAdapter(new Tadapter(MainActivity.this));
                        break;
                    case R.id.rb_3:
                        List<Map<String, Object>> listItem = new ArrayList<>();
                        HashMap<String, Object> map1 = new HashMap<String, Object>();
                        HashMap<String, Object> map2 = new HashMap<String, Object>();
                        HashMap<String, Object> map3 = new HashMap<String, Object>();
                        HashMap<String, Object> map4 = new HashMap<String, Object>();
                        HashMap<String, Object> map5 = new HashMap<String, Object>();
                        HashMap<String, Object> map6 = new HashMap<String, Object>();

                        map1.put("ItemTitle", "美国谷歌公司已发出");
                        map1.put("ItemText", "发件人:谷歌 CEO Sundar Pichai");
                        listItem.add(map1);

                        map2.put("ItemTitle", "国际顺丰已收入");
                        map2.put("ItemText", "等待中转");
                        listItem.add(map2);

                        map3.put("ItemTitle", "国际顺丰转件中");
                        map3.put("ItemText", "下一站中国");
                        listItem.add(map3);

                        map4.put("ItemTitle", "中国顺丰已收入");
                        map4.put("ItemText", "下一站广州华南理工大学");
                        listItem.add(map4);

                        map5.put("ItemTitle", "中国顺丰派件中");
                        map5.put("ItemText", "等待派件");
                        listItem.add(map5);

                        map6.put("ItemTitle", "华南理工大学已签收");
                        map6.put("ItemText", "收件人:Carson");
                        listItem.add(map6);

                        mRecyclerView.setAdapter(new TimeLineAdapter(listItem));
                        mDecoration = new TimeLineDecoration(MainActivity.this);
                        mRecyclerView.addItemDecoration(mDecoration);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    default:
                        break;
                }
            }
        });
        findViewById(R.id.group_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GroupListActivity.class);
                startActivity(intent);
            }
        });
    }
}
