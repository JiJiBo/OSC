package com.example.administrator.osc.http;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpSend {
    private static Context mc;

    public HttpSend(Context mc) {
        this.mc = mc;
    }

    public static final String MAIN_URL = "http://192.168.1.6:8080";

    public static InputStream send(String httpURL) {
        try {
            URL url = new URL(httpURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(4000);
            conn.setConnectTimeout(4000);
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
