package com.example.administrator.osc.ENUM;

import com.example.administrator.osc.Fragment.DongTanFragment;
import com.example.administrator.osc.Fragment.FaXianFragment;
import com.example.administrator.osc.Fragment.WoFragment;
import com.example.administrator.osc.Fragment.ZongHeFragment;
import com.example.administrator.osc.R;

public enum MainTab {
    ZongHe(0, "综合", "ZongHe", R.drawable.tab_icon_new, ZongHeFragment.class), DongTan(1, "动弹", "DongTan", R.drawable.tab_icon_tweet, DongTanFragment.class), KongBai(2, "空白", "KongBai", R.drawable.tab_icon_new, null), FaXian(3, "发现", "FaXian", R.drawable.tab_icon_explore, FaXianFragment.class), Wo(4, "我", "Wo", R.drawable.tab_icon_me, WoFragment.class);

    private final int id;
    private final String name;
    private final String Key;
    private final int iconId;
    private final Class<?> clazz;

    MainTab(int id, String name, String Key, int iconId, Class<?> clazz) {
        this.id = id;
        this.name = name;
        this.Key = Key;
        this.iconId = iconId;
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return Key;
    }

    public int getIconId() {
        return iconId;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
