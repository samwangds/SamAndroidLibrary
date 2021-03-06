package com.sam.lib.app;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.sam.lib.common.ExitHandler;


/**
 * 基础activity 类
 * Created by Sam on 2015/7/2.
 */
public class BaseActivity extends AppCompatActivity {

    protected Application mApplication;
    protected BaseActivity act;
    private ProgressDialog mDialog ;
    private final static int EXIT_TIME_DELAY = 1000;
    private final static int EXIT_MSG_WHAT = 1;
    private final static int EXIT_MSG_OUT_TIME_WHAT = 2;
    private boolean isBackClicked = false;
    public final static String KEY_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);

        if(getLayoutRes() > 0 ){
            setContentView(getLayoutRes());
        }
        mApplication =  getApplication();
        act = this;

        initView();
        initData();
        initEvent();
    }

    protected int getLayoutRes() {
        return -1;
    }

    /**
     * 初始化视图
     */
    protected void initView(){

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
     * 显示 toast
     * @param resId 资源ID
     */
    public void toast(int resId){
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 toast
     * @param text str
     */
    public void toast(CharSequence text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回键处理
     * @param v
     */
    public void onBackBtnClick(View v){
        onBackPressed();
    }

    public void exitApp() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    protected void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    protected void startActivityFinishSelf(Class<?> cls){
        startActivity(cls);
        finish();
    }

    public ProgressDialog getDialog() {
        if(mDialog == null){
            mDialog = new ProgressDialog(act);
            mDialog.setCancelable(true);
            mDialog.setTitle(null);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    onProgressDialogCancel();
                }
            });
        }
        return mDialog;
    }

    /**
     * 进度框取消回调
     */
    protected void onProgressDialogCancel(){
//        AppResultUtil.recycleTask(mTask);
    }

    public void showProgressDialog(CharSequence message){
       getDialog().setMessage(message);
        getDialog().show();
    }

    public void showProgressDialog(int strResId){
        showProgressDialog(act.getString(strResId));
    }

    public void dismissProgressDialog(){
        if(mDialog !=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }


    private ExitHandler mHandler;


    /**
     * 双击返回退出
     */
    protected void onDoubleBackExit(){
        if (mHandler == null) {
            mHandler = new ExitHandler();
        }
        if(mHandler.isBackClicked()){
            exitApp();
        }else {
            toast("再次点击返回键退出");
        }
    }


}
