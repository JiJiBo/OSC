package com.example.administrator.osc.Fragment;

import com.example.administrator.osc.Adapter.ViewPageFragmentAdapter;
import com.example.administrator.osc.base.BaseTabFragment;

public class ZongHeFragment extends BaseTabFragment {


    @Override
    public void setUpAdapter(ViewPageFragmentAdapter mAdapter) {
        mAdapter.addTab("资讯", "zisun", ZiXunFragment.class, null);
        mAdapter.addTab("热点", "redian", ReDianFragment.class, null);
        mAdapter.addTab("博客", "boke", BoKeFragment.class, null);
        mAdapter.addTab("推荐", "tuijian", TuiJianFragment.class, null);
    }
}
