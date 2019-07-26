package com.lwj.skin.fetcher;

import com.lwj.skin.apply.DrawableBottomApply;
import com.lwj.skin.fetcher.base.CompoundDrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class DrawableBottomFetcher extends CompoundDrawableFetcher<DrawableBottomApply> {




    @Override
    public String getAttrName() {
        return "drawableBottom";
    }

    @Override
    public DrawableBottomApply getSkiApply() {
        return new DrawableBottomApply();
    }
}
