package com.sam.lib.app;

import android.os.Bundle;
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

    protected View findViewById(int id){
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

}
