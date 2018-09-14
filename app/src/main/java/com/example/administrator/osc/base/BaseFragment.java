package com.example.administrator.osc.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.osc.view.LoadingView;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment  {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadingView view = new LoadingView(getContext()) {
            @Override
            public View LoadView() {

                return BaseFragment.this.LoadView();
            }

            @Override
            public STATE_LOAD LoadData() {
                return BaseFragment.this.LoadData();
            }
        };
        view.onLoad();
        return view;
    }

    /**
     * 先执行
     */
    protected abstract LoadingView.STATE_LOAD LoadData();

    /**
     * 后执行
     */
    protected abstract View LoadView();

    /**
     * 检查取得的数据
     * @param data 取得的数据
     * @return 数据检查结果
     */
    protected LoadingView.STATE_LOAD checkData(ArrayList data) {
        if (data == null || data.size() == 0) {
            return LoadingView.STATE_LOAD.STATE_ERROR;
        } else {
            return LoadingView.STATE_LOAD.STATE_SUCCESS;
        }
    }
}
