package com.example.administrator.osc.Fragment;

import android.view.View;

import com.example.administrator.osc.base.BaseFragment;
import com.example.administrator.osc.view.LoadingView;

public class ZuiXinDongTanFragment extends BaseFragment {
    @Override
    protected LoadingView.STATE_LOAD LoadData() {
        return checkData(null);
    }

    @Override
    protected View LoadView() {
        return null;
    }
}
