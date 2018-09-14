package com.example.administrator.osc.Fragment;

import com.example.administrator.osc.Adapter.ViewPageFragmentAdapter;
import com.example.administrator.osc.base.BaseTabFragment;

public class DongTanFragment extends BaseTabFragment {
    @Override
    public void setUpAdapter(ViewPageFragmentAdapter mAdapter) {
        mAdapter.addTab("最新动弹", "zuixindongtan", ZuiXinDongTanFragment.class, null);
        mAdapter.addTab("热门动弹", "remendongtan", ReMenDongTanFragment.class, null);
        mAdapter.addTab("我的动弹", "wodedongtan", WoDeDongTanFragment.class, null);
    }
}
