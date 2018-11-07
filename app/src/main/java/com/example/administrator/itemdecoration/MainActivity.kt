package com.example.administrator.itemdecoration

import android.os.Bundle
import android.support.v7.app.AlertDialog.Builder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.administrator.itemdecoration.adapter.GroupAdapter
import com.example.administrator.itemdecoration.adapter.Tadapter
import com.example.administrator.itemdecoration.adapter.TimeLineAdapter
import com.example.administrator.itemdecoration.callback.GroupCallback
import com.example.administrator.itemdecoration.callback.GroupImpl
import com.example.administrator.itemdecoration.itemdecoration.HeaderDecoration
import com.example.administrator.itemdecoration.itemdecoration.NormalDecoration
import com.example.administrator.itemdecoration.itemdecoration.StickyHeaderDecoration
import com.example.administrator.itemdecoration.itemdecoration.TimeLineDecoration
import com.example.administrator.itemdecoration.support.utils.ListUtils

class MainActivity : AppCompatActivity() {

    private var mDecoration: RecyclerView.ItemDecoration? = null
    private var mLayoutManager: LayoutManager? = null
    private var mToolbar: android.support.v7.widget.Toolbar? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private val items = arrayOf("竖向分割线", "横向分割线", "时间轴分割线", "含有Header分割线", "粘性头部分割线")
    private var mCallback: GroupCallback? = null
    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        mToolbar?.title = items[0]
        mRecyclerView = findViewById(R.id.recycler)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mDecoration = NormalDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
        mRecyclerView!!.adapter = Tadapter(this@MainActivity)
        mRecyclerView!!.addItemDecoration(mDecoration ?: return)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.more_in_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.more) {
            val builder = Builder(this)
            builder.setTitle("分割线样式")
            builder.setItems(items) { dialog, which ->
                if (mDecoration != null) {
                    mRecyclerView!!.removeItemDecoration(mDecoration ?: return@setItems)
                }
                when (which) {
                    0 -> {
                        mDecoration = NormalDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                        mAdapter = Tadapter(this@MainActivity)
                    }
                    1 -> {
                        mLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        mDecoration = NormalDecoration(this@MainActivity, LinearLayoutManager.HORIZONTAL)
                        mRecyclerView!!.layoutManager = mLayoutManager
                        mAdapter = Tadapter(this@MainActivity)
                    }
                    2 -> {
                        mLayoutManager = LinearLayoutManager(this@MainActivity)
                        mDecoration = TimeLineDecoration(this@MainActivity)
                        mAdapter = TimeLineAdapter(ListUtils.mapList)
                    }
                    3 -> {
                        mLayoutManager = LinearLayoutManager(this@MainActivity)
                        mCallback = GroupImpl()
                        mAdapter = GroupAdapter(ListUtils.getNumList(50))
                        mDecoration = HeaderDecoration(this@MainActivity, mCallback)
                    }
                    4 -> {
                        mLayoutManager = LinearLayoutManager(this@MainActivity)
                        mAdapter = GroupAdapter(ListUtils.getNumList(50))
                        mCallback = GroupImpl()
                        mDecoration = StickyHeaderDecoration(this@MainActivity, mCallback)
                    }
                    else -> {
                    }
                }
                mToolbar?.title = items[which]
                mRecyclerView!!.layoutManager = mLayoutManager
                mRecyclerView!!.addItemDecoration(mDecoration ?: return@setItems)
                mRecyclerView!!.adapter = mAdapter
            }
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }
}
