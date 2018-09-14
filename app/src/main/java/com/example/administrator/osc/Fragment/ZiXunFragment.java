package com.example.administrator.osc.Fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.osc.Adapter.ZiXunAdapter;
import com.example.administrator.osc.R;
import com.example.administrator.osc.base.BaseFragment;
import com.example.administrator.osc.bean.NewsList;
import com.example.administrator.osc.http.getHttpData;
import com.example.administrator.osc.util.XmlUtils;
import com.example.administrator.osc.view.LoadingView;

import java.io.InputStream;
import java.util.ArrayList;

public class ZiXunFragment extends BaseFragment {

    private ListView mListView;
    private NewsList mNewsList;
    private ZiXunAdapter mAdapter;
    private SwipeRefreshLayout mSrl;
    private ArrayList mNewsListList;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
            if (mSrl != null) {
                mSrl.setRefreshing(false);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected LoadingView.STATE_LOAD LoadData() {
        InputStream is = getHttpData.getDataFromHttp(getHttpData.TYPE_ZI_XUN, 0);
        if (is == null) {
            return LoadingView.STATE_LOAD.STATE_ERROR;
        }
        mNewsList = XmlUtils.toBean(NewsList.class, is);
        return checkData((ArrayList) mNewsList.getList());
    }

    @Override
    protected View LoadView() {
        View view = View.inflate(getContext(), R.layout.fragment_zi_xun, null);
        mListView = view.findViewById(R.id.listview);
        mSrl = view.findViewById(R.id.srl);
        mNewsListList = (ArrayList) mNewsList.getList();
        mAdapter = new ZiXunAdapter(mNewsListList, getContext());
        mListView.setAdapter(mAdapter);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        InputStream is = getHttpData.getDataFromHttp(getHttpData.TYPE_ZI_XUN, 0);

                        NewsList list = XmlUtils.toBean(NewsList.class, is);
                        mNewsListList = (ArrayList) list.getList();
                        mHandler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });
        return view;
    }
}
