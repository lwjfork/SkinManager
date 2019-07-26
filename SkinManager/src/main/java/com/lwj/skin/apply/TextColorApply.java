package com.lwj.skin.apply;

import android.view.View;
import android.widget.TextView;

import com.lwj.skin.apply.base.ColorApply;
import com.lwj.skin.fetcher.TextColorFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class TextColorApply extends ColorApply<TextColorFetcher> {


    @Override
    public void apply(View view, int color) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setTextColor(color);
        }
    }
}
