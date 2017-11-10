package com.example.administrator.itemdecoration;

import java.util.ArrayList;
import java.util.List;

import com.example.administrator.itemdecoration.adapter.GroupAdapter;
import com.example.administrator.itemdecoration.bean.GroupInfo;
import com.example.administrator.itemdecoration.itemdecoration.GroupDecoration;
import com.example.administrator.itemdecoration.itemdecoration.GroupDecoration.GroupCallback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class GroupListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    GroupAdapter mGroupAdapter;
    List<String> mGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        mRecyclerView = findViewById(R.id.group_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initData();
    }

    private void initData(){
        for (int i = 0; i < 50; i++) {
            mGroups.add(i + "");
        }
        mGroupAdapter = new GroupAdapter(mGroups);
        mRecyclerView.setAdapter(mGroupAdapter);
        GroupCallback callback = new GroupCallback() {
            @Override
            public GroupInfo group(final int position) {

                int groupId = position / 5;
                int index = position % 5;
                GroupInfo info = new GroupInfo();
                info.setPosition(index);
                info.setTitle(groupId + "");
                info.setGroupId(groupId + "");
                return info;
            }
        };
        mRecyclerView.addItemDecoration(new GroupDecoration(this, callback));
    }
}
