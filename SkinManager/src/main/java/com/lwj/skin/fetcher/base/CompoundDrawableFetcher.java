package com.lwj.skin.fetcher.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.lwj.skin.apply.base.DrawableApply;


/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public abstract class CompoundDrawableFetcher<V extends DrawableApply> extends DrawableFetcher<V> {


    @Override
    public Drawable getRes(Resources res, int resId) {
        Drawable drawable = super.getRes(res, resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

}