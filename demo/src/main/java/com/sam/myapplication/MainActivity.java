package com.sam.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sam.lib.impl.DefaultClickEffectTranslucence;
import com.sam.lib.widget.listener.OnClickEffectTouchListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View tv = findViewById(R.id.tv);
        tv.setOnTouchListener(new OnClickEffectTouchListener());

        ImageView iv = (ImageView) findViewById(R.id.iv);
        OnClickEffectTouchListener onClickEffectTouchListener = new OnClickEffectTouchListener();
        onClickEffectTouchListener.setViewClickEffect(new DefaultClickEffectTranslucence());
        iv.setOnTouchListener(onClickEffectTouchListener);
    }

}
