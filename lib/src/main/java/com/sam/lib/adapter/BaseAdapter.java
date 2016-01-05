package com.sam.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Sam
 * @date 2015/6/17
 * @time 13:44
 * @description
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {

    protected Context mContext;

    public List<Object> getData() {
        return mData;
    }

    public void setData(List<Object> data) {
        mData = data;
    }

    protected List<Object> mData;


    public BaseAdapter(Context context, List data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        if(mData != null && position < mData.size()){
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public abstract int getItemLayoutResId();


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if ( convertView == null ) {
            convertView = LayoutInflater.from( mContext ).inflate(getItemLayoutResId(), null );
            setViewHolder(convertView);
        }
        bindData(position, convertView);
        return convertView;
    }

    protected void bindData(int position, View convertView){
        bindData(getItem(position), convertView);
    }


    protected abstract void setViewHolder(View convertView);

    /**
     * 绑定数据
     * @param itemData
     * @param convertView
     */
    protected abstract void bindData(Object itemData, View convertView);

}
