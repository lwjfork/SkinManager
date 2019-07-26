package com.lwj.skin.fetcher;

import com.lwj.skin.apply.DrawableRightApply;
import com.lwj.skin.fetcher.base.CompoundDrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class DrawableRightFetcher extends CompoundDrawableFetcher<DrawableRightApply> {



    @Override
    public String getAttrName() {
        return "drawableRight";
    }

    @Override
    public DrawableRightApply getSkiApply() {
        return new DrawableRightApply();
    }
}
