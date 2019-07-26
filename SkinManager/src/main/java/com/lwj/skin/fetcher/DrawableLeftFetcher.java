package com.lwj.skin.fetcher;

import com.lwj.skin.apply.DrawableLeftApply;
import com.lwj.skin.fetcher.base.CompoundDrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class DrawableLeftFetcher extends CompoundDrawableFetcher<DrawableLeftApply> {




    @Override
    public String getAttrName() {
        return "drawableLeft";
    }

    @Override
    public DrawableLeftApply getSkiApply() {
        return new DrawableLeftApply();
    }
}

