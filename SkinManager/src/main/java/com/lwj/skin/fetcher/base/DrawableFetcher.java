package com.lwj.skin.fetcher.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.lwj.skin.apply.base.DrawableApply;
import com.lwj.skin.util.SkinResUtil;

public abstract class DrawableFetcher<V extends DrawableApply> implements ResFetcher<Drawable, V> {

    @Override
    public Drawable getRes(Resources res, int resId) {
        return SkinResUtil.getDrawable(res, resId);
    }


    @Override
    public String getTypeName() {
        return "drawable";
    }


}