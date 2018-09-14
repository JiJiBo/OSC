package com.example.administrator.osc.http;

import android.util.Log;

import com.android.volley.Response;

import java.io.InputStream;

import static com.android.volley.VolleyLog.TAG;

public class getHttpData {
    public static final int TYPE_ZI_XUN = 0;
    public static String ZiXun = "/oschina/list/news";

    public static InputStream getDataFromHttp(int type, int page) {
        Log.e(TAG, "getDataFromHttp: page"+page );
        String url = getURL(type, page);
        return HttpSend.send(url);
    }

    private static String getURL(int type, int page) {
        String url = HttpSend.MAIN_URL;
        switch (type) {
            case TYPE_ZI_XUN:
                url += ZiXun + "/page" + page + ".xml";
                break;
        }
        return url;
    }
}
