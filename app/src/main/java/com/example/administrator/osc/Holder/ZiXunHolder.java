package com.example.administrator.osc.Holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.osc.R;
import com.example.administrator.osc.ZiXunActivity;
import com.example.administrator.osc.base.BaseViewHolder;
import com.example.administrator.osc.bean.News;
import com.example.administrator.osc.util.spUtils;

public class ZiXunHolder extends BaseViewHolder<News> {

    private final TextView mTv_title;
    private final TextView mTv_des;
    private final TextView mTv_author;
    private final TextView mTv_time;
    private final TextView mTv_comment;

    public ZiXunHolder(Context mc) {
        super(mc);
        mTv_title = getRootView().findViewById(R.id.tv_title);
        mTv_des = getRootView().findViewById(R.id.tv_des);
        mTv_author = getRootView().findViewById(R.id.tv_author);
        mTv_time = getRootView().findViewById(R.id.tv_time);
        mTv_comment = getRootView().findViewById(R.id.tv_comment);
    }

    @Override
    public View initView() {
        View view = View.inflate(mc, R.layout.detail_listview_zixun, null);

        return view;
    }

    @Override
    public void refreshView(final News data) {
        mTv_title.setText(data.getTitle());
        mTv_des.setText(data.getBody());
        mTv_author.setText(data.getAuthor());
        mTv_time.setText(data.getPubDate());
        mTv_comment.setText(data.getCommentCount() + "");
        mTv_title.setTextColor(Color.BLACK);
        String opens = spUtils.getSpString(mc, ConstantValue.ZIXUNOPENS, "");
        if (opens.contains(data.getId() + "")) {
            mTv_title.setTextColor(Color.rgb(0xaa, 0xaa, 0xaa));
        }
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到已经被打开过的页面ID
                String opens = spUtils.getSpString(mc, ConstantValue.ZIXUNOPENS, "");

                if (!opens.contains(data.getId() + "")) {
                    //如果没有被打开过,就把ID写入配置信息
                    spUtils.putSpString(mc, ConstantValue.ZIXUNOPENS, opens + "---" + data.getId());
                }
                mTv_title.setTextColor(Color.rgb(0xaa, 0xaa, 0xaa));
                Intent intent = new Intent(mc, ZiXunActivity.class);
                intent.putExtra("url", "oschina/detail/news_detail/" + data.getId() + ".xml");
                intent.putExtra("news_id",data.getId());
                mc.startActivity(intent);
            }
        });
    }

}
