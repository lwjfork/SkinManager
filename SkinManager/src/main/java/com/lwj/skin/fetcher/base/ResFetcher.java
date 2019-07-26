package com.lwj.skin.fetcher.base;

import android.content.res.Resources;

import com.lwj.skin.apply.base.SkinApply;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 * 资源抓取器
 * android:textColor = "@color/black"
 */

public interface ResFetcher<T, V extends SkinApply> {


    T getRes(Resources res, int resId);

    // textColor
    String getAttrName();

    //  @color/black   color
    String getTypeName();


    V getSkiApply();

}
