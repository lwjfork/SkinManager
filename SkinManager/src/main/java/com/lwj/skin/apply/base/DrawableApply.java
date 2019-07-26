package com.lwj.skin.apply.base;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.lwj.skin.SkinManager;
import com.lwj.skin.fetcher.base.DrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public abstract class DrawableApply<T extends DrawableFetcher> implements SkinApply<T> {


    @Override
    public void apply(View view, int resId, T res) {
        apply(view, getDrawable(resId, res));
    }


    protected Drawable getDrawable(int resId, T res) {
        return res.getRes(SkinManager.getInstance().getSkinRes(), resId);
    }

    public abstract void apply(View view, Drawable drawable);
}
