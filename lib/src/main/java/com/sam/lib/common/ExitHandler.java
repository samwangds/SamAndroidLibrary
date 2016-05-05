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

    public boolean isBackClicked() {
        return isBackClicked;
    }

    public void setBackClicked(boolean backClicked) {
        isBackClicked = backClicked;
    }
}
