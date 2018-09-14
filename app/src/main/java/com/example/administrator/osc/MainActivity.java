package com.example.administrator.osc;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.osc.ENUM.MainTab;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayoput;
    private boolean isOpen = false;

    public static Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initDrawerLayout();
        initToolBar();
        initTabHost();

    }

    private void initDrawerLayout() {
        mDrawerLayoput.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                isOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    private void toogle() {
        if (isOpen) {
            mDrawerLayoput.closeDrawers();
        } else {
            mDrawerLayoput.openDrawer(Gravity.START);
        }
        isOpen = !isOpen;
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        MainTab[] mainTabs = MainTab.values();
        int len = mainTabs.length;
        for (int i = 0; i < len; i++) {
            MainTab tab = mainTabs[i];
            //创建TabSpec
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getKey());
            //得到布局
            View indicator = View.inflate(this, R.layout.view_indicator, null);
            ImageView iv_tab = indicator.findViewById(R.id.iv_tab);
            TextView tv_tab = indicator.findViewById(R.id.tv_tab);

            //设置参数
            iv_tab.setBackground(getDrawable(tab.getIconId()));
            tv_tab.setText(tab.getName());
            //隐藏
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
            }
            tabSpec.setIndicator(indicator);
            //数据
            Bundle bundle = new Bundle();
            bundle.putString("data", tab.getName());
            mTabHost.addTab(tabSpec, tab.getClazz(), bundle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toogle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        mTabHost = findViewById(R.id.ftb);
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayoput = findViewById(R.id.drawerLayoput);
    }
}
