package com.sam.lib.app;

import android.app.ActivityManager;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


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
    protected void toast(int resId){
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 toast
     * @param text str
     */
    protected void toast(CharSequence text){
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
        String packName = getPackageName();
        ActivityManager activityMgr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityMgr.killBackgroundProcesses(packName);
        finish();
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


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case EXIT_MSG_WHAT:
                    isBackClicked = false;
                    break;
                case EXIT_MSG_OUT_TIME_WHAT:
                    mHandler.removeMessages(EXIT_MSG_WHAT);
                    break;

            }
            return false;
        }
    });

    /**
     * 双击返回退出
     */
    protected void onDoubleBackExit(){
        if(isBackClicked){
            mHandler.removeMessages(EXIT_MSG_WHAT);
            exitApp();
//            finish();
        }else {
            isBackClicked = true;
            mHandler.sendEmptyMessageDelayed(EXIT_MSG_OUT_TIME_WHAT, EXIT_TIME_DELAY);
            toast("再次点击返回键退出");
        }
    }


}
