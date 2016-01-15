package com.sam.lib.interfaces;

import android.view.View;

/**
 * View点击效果接口
 * @author SamWang(199004)
 *         2016/1/15	14:05
 */
public interface ViewClickEffect {
    /**
     * 按下去的效果
     * @param view
     */
    void onPressedEffect(View view);

    /**
     * 释放的效果
     * @param view
     */
    void onUnPressedEffect(View view);
}
