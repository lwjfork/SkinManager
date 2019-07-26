package com.lwj.test;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lwj.skin.SkinManager;
import com.lwj.skin.util.SkinInflaterFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected SkinInflaterFactory skinInflaterFactory;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SkinManager.getInstance().init(getApplicationContext());
        skinInflaterFactory = new SkinInflaterFactory();
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), skinInflaterFactory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        final ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("fadsfadsfasdfdasf");
        }
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return strings.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                    holder = new ViewHolder();
                    holder.tv = convertView.findViewById(R.id.tv_text);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.tv.setText(strings.get(position));
                return convertView;
            }
        });
    }

    public class ViewHolder {

        public TextView tv;

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
        if (!isSkin) {
            SkinManager.getInstance().loadSkin("/sdcard/skin/skin-debug.apk");
            SkinManager.getInstance().apply(skinInflaterFactory);
        } else {
            SkinManager.getInstance().loadSkin("");
            SkinManager.getInstance().apply(skinInflaterFactory);
        }

        isSkin = !isSkin;
    }
}
