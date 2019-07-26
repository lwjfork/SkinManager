package com.lwj.test;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lwj.skin.SkinManager;
import com.lwj.skin.util.SkinInflaterFactory;

public class MainActivity extends AppCompatActivity {

    protected SkinInflaterFactory skinInflaterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SkinManager.getInstance().init(getApplicationContext());
        skinInflaterFactory = new SkinInflaterFactory();
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), skinInflaterFactory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().apply(skinInflaterFactory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    boolean isSkin = false;

    public void changeSkin(View view) {
       if(!isSkin){
           SkinManager.getInstance().loadSkin("/sdcard/skin/skin-debug.apk");
           SkinManager.getInstance().apply(skinInflaterFactory);
       }else {
           SkinManager.getInstance().loadSkin("");
           SkinManager.getInstance().apply(skinInflaterFactory);
       }

        isSkin = !isSkin;
    }
}
