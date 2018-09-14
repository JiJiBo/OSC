package com.example.administrator.osc.Holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.osc.R;
import com.example.administrator.osc.base.BaseViewHolder;

public class MoreHolder extends BaseViewHolder<String> {

    private TextView mTextView;

    public MoreHolder(Context mc) {
        super(mc);
    }

    @Override
    public View initView() {
        View view = View.inflate(mc, R.layout.view_only_textview, null);
        mTextView = view.findViewById(R.id.textview);
        mTextView.setText("加载更多...");
        return mTextView;
    }

    @Override
    public void refreshView(String data) {
        mTextView.setText(data);
    }
}
