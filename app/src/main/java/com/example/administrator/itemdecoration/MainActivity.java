package com.example.administrator.itemdecoration;

import com.example.administrator.itemdecoration.adapter.GroupAdapter;
import com.example.administrator.itemdecoration.adapter.Tadapter;
import com.example.administrator.itemdecoration.adapter.TimeLineAdapter;
import com.example.administrator.itemdecoration.callback.GroupCallback;
import com.example.administrator.itemdecoration.callback.GroupImpl;
import com.example.administrator.itemdecoration.itemdecoration.HeaderDecoration;
import com.example.administrator.itemdecoration.itemdecoration.NormalDecoration;
import com.example.administrator.itemdecoration.itemdecoration.StickyHeaderDecoration;
import com.example.administrator.itemdecoration.itemdecoration.TimeLineDecoration;
import com.example.administrator.itemdecoration.support.utils.ListUtils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.ItemDecoration mDecoration;
    private LayoutManager mLayoutManager;
    private android.support.v7.widget.Toolbar mToolbar;
    private RecyclerView.Adapter mAdapter;
    private final String [] items = new String[]{"竖向分割线", "横向分割线", "时间轴分割线", "含有Header分割线", "粘性头部分割线"};
    private GroupCallback mCallback;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView = findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDecoration = new NormalDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(new Tadapter(MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more_in_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.more:
                AlertDialog.Builder builder = new Builder(this);
                builder.setTitle("分割线样式");
                builder.setItems(items, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mDecoration != null){
                            mRecyclerView.removeItemDecoration(mDecoration);
                        }
                        switch (which){
                            case 0:
                                mDecoration = new NormalDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                                mAdapter = new Tadapter(MainActivity.this);
                                break;
                            case 1:
                                mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                mDecoration = new NormalDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mAdapter = new Tadapter(MainActivity.this);
                                break;
                            case 2:
                                mDecoration = new TimeLineDecoration(MainActivity.this);
                                mAdapter = new TimeLineAdapter(ListUtils.getMapList());
                                break;
                            case 3:
                                mCallback = new GroupImpl();
                                mAdapter = new GroupAdapter(ListUtils.getNumList(50));
                                mDecoration = new HeaderDecoration(MainActivity.this, mCallback);
                                break;
                            case 4:
                                mAdapter = new GroupAdapter(ListUtils.getNumList(50));
                                mCallback = new GroupImpl();
                                mDecoration = new StickyHeaderDecoration(MainActivity.this, mCallback);
                                break;
                        }
                        mRecyclerView.addItemDecoration(mDecoration);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
