package com.example.administrator.osc.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.osc.Adapter.ViewPageFragmentAdapter;
import com.example.administrator.osc.R;
import com.example.administrator.osc.view.PagerSlidingTabStrip;

public abstract class BaseTabFragment extends Fragment {

    private PagerSlidingTabStrip mPager_tabstrip;
    private ViewPager mVp_detail;
    private ViewPageFragmentAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_tab, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPager_tabstrip = view.findViewById(R.id.pager_tabstrip);
        mVp_detail = view.findViewById(R.id.vp_detail);
        mAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(), mPager_tabstrip, mVp_detail);
        setUpAdapter(mAdapter);
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract void setUpAdapter(ViewPageFragmentAdapter mAdapter);
}
