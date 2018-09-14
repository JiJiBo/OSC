package com.example.administrator.osc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.administrator.osc.bean.News;
import com.example.administrator.osc.http.HttpSend;

public class ZiXunActivity extends AppCompatActivity {

    private static final String TAG = "ZiXunActivity";
    private Toolbar mToolbar;

    private WebView mWebView;
    private News mNews;
    private int mNewsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun);
        initUI();
        initToolBar();
        getData();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            initData();
        }
    };

    private void initData() {
    }

    private void getData() {
        Intent intent = getIntent();
        mNewsId = intent.getIntExtra("news_id", 0);
        String url = HttpSend.MAIN_URL + "/" + intent.getStringExtra("url");
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initUI() {
        mToolbar = findViewById(R.id.toolbar);
        mWebView = findViewById(R.id.wb_zixun);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.zixun_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
