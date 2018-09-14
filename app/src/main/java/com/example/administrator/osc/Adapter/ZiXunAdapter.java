package com.example.administrator.osc.Adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.osc.base.BaseViewHolder;
import com.example.administrator.osc.Holder.ZiXunHolder;
import com.example.administrator.osc.base.BaseListViewAdapter;
import com.example.administrator.osc.bean.NewsList;
import com.example.administrator.osc.http.getHttpData;
import com.example.administrator.osc.util.XmlUtils;

import java.io.InputStream;
import java.util.ArrayList;

public class ZiXunAdapter extends BaseListViewAdapter {
    private final Context mc;
    private int page = 0;

    public ZiXunAdapter(ArrayList data, Context mc) {
        super(data, mc);
        this.mc = mc;
    }

    @Override
    protected ArrayList LoadMoreData() {

        InputStream is = getHttpData.getDataFromHttp(getHttpData.TYPE_ZI_XUN, ++page);
        if (is == null) {
            return null;
        }
        NewsList list = XmlUtils.toBean(NewsList.class, is);
        return (ArrayList) list.getList();
    }

    @Override
    public BaseViewHolder getHolder(View convertView) {

        return new ZiXunHolder(mc);
    }
}
