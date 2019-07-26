package com.lwj.skin.apply.base;

import android.support.annotation.ColorInt;
import android.view.View;

import com.lwj.skin.SkinManager;
import com.lwj.skin.fetcher.base.ColorFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public abstract class ColorApply<T extends ColorFetcher> implements SkinApply<T> {


    @Override
    public void apply(View view, int resId, T res) {
        int color = res.getRes(SkinManager.getInstance().getSkinRes(), resId);
        apply(view, color);
    }

    public abstract void apply(View view, @ColorInt int color);
}
