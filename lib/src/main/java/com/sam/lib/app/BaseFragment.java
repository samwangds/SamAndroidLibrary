package com.sam.lib.app;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 基础Fragment类
 * @author SamWang(199004)
 * @date 2016/1/5	14:09
 */
public abstract class BaseFragment extends Fragment{
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            if (isHidden) {
                getFragmentManager().beginTransaction().hide(this).commit();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化视图
     */
    protected void initView(){

    }

    protected View findViewById(@IdRes int id){
        if(getView() != null ){
            return getView().findViewById(id);
        }
        return null;
    }

    /**
     * 初始化数据
     */
    protected void initData(){

    }

    /**
     * 初始化事件
     */
    protected void initEvent(){

    }

    /**
     * 视图
     * @return
     */
    protected abstract int getLayoutRes();

    /**
     * 显示 toast
     * @param resId 资源ID
     */
    public void toast(int resId){
        Toast.makeText(getContext(),resId,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 toast
     * @param text str
     */
    public void toast(CharSequence text){
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
    }

    //回收时保存状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}
