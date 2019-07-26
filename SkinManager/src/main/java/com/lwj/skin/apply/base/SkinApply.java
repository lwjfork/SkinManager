package com.lwj.skin.apply.base;

import android.view.View;

import com.lwj.skin.fetcher.base.ResFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public interface SkinApply<T extends ResFetcher> {


    void apply(View view, int resId, T res);


}
