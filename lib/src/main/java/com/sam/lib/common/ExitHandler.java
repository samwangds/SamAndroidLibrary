package com.sam.lib.common;

import android.os.Handler;
import android.os.Message;

/**
 * @author SamWang(199004)
 *         2016/5/5 16:38
 */
public class ExitHandler extends Handler {
    public final static int EXIT_MSG_WHAT = 1;
    public final static int EXIT_MSG_OUT_TIME_WHAT = 2;
    private boolean isBackClicked = false;
    private final static int EXIT_TIME_DELAY = 1000;

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case EXIT_MSG_OUT_TIME_WHAT:
                isBackClicked = false;
                break;
            case EXIT_MSG_WHAT:
                isBackClicked = true;
                break;
        }
    }

    /**
     * 是否已经点了返回键，
     * @return true 已点一次（直接退出），false 未点过（第一次，应该toast）
     */
    public boolean isBackClicked() {
        if(!isBackClicked){
            sendEmptyMessage(ExitHandler.EXIT_MSG_WHAT);
            sendEmptyMessageDelayed(ExitHandler.EXIT_MSG_OUT_TIME_WHAT, EXIT_TIME_DELAY);
        }
        return isBackClicked;
    }

    public void setBackClicked(boolean backClicked) {
        isBackClicked = backClicked;
    }
}
