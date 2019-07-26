package com.lwj.skin.fetcher.base;

import android.content.res.Resources;
import android.support.annotation.ColorInt;

import com.lwj.skin.apply.base.ColorApply;
import com.lwj.skin.util.SkinResUtil;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public abstract class ColorFetcher<V extends ColorApply> implements ResFetcher<Integer, V> {

    @Override
    @ColorInt
    public Integer getRes(Resources res, int resId) {
        return SkinResUtil.getColor(res, resId);
    }

    @Override
    public String getTypeName() {
        return "color";
    }
}
