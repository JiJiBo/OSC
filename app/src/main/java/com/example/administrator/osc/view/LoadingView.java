package com.example.administrator.osc.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.osc.MainActivity;
import com.example.administrator.osc.R;

import static android.content.ContentValues.TAG;

public abstract class LoadingView extends FrameLayout {

    private static final int STATE_NONE = 0;
    private static final int STATE_UNDO = 1;
    private static final int STATE_ON_LOAD = 2;
    private static final int STATE_LOAD_ERROR = 3;
    private static final int STATE_SUCCESSFUL = 4;

    private int STATE_NOW = STATE_NONE;
    private View mView_onloading;
    private View mView_loading_error;
    private View mView_successful = null;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //加载正在加载布局
        mView_onloading = View.inflate(getContext(), R.layout.view_onloading, null);
        addView(mView_onloading);

        //加载加载失败布局s
        mView_loading_error = View.inflate(getContext(), R.layout.view_loading_error, null);
        addView(mView_loading_error);

        mView_loading_error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                STATE_NOW = STATE_UNDO;
                Log.e(TAG, "onClick: 重新加载");
                selectRightView();
                onLoad();
            }
        });
        selectRightView();
    }

    private void selectRightView() {
        mView_onloading.setVisibility(STATE_NOW == STATE_ON_LOAD ? View.VISIBLE : View.GONE);
        mView_loading_error.setVisibility(STATE_NOW == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);

        //加载成功布局
        if (mView_successful == null && STATE_NOW == STATE_SUCCESSFUL) {
            mView_successful = LoadView();
            if (mView_successful != null) {
                addView(mView_successful);
            }
        }

        if (mView_successful != null) {
            mView_successful.setVisibility(STATE_NOW == STATE_SUCCESSFUL ? View.VISIBLE : View.GONE);
        }
    }


    public void onLoad() {
        if (STATE_NOW == STATE_UNDO || STATE_NOW == STATE_NONE) {
            //如果状态是没做什么
            STATE_NOW = STATE_ON_LOAD;
            selectRightView();
            new Thread() {
                @Override
                public void run() {
                    //加载数据，并获得返回码
                    final STATE_LOAD current_state = LoadData();
                    MainActivity.uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //解析的返回码，刷新布局
                            STATE_NOW = current_state.getState();
                            selectRightView();
                        }
                    });
                }
            }.start();
        }
    }

    /**
     * 后执行
     */
    public abstract View LoadView();

    /**
     * 先执行
     */
    public abstract STATE_LOAD LoadData();

    public enum STATE_LOAD {
        STATE_ERROR(STATE_LOAD_ERROR), STATE_SUCCESS(STATE_SUCCESSFUL);
        private final int state;

        STATE_LOAD(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
}
