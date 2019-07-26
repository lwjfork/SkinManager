package com.lwj.skin.fetcher;

import com.lwj.skin.apply.DrawableTopApply;
import com.lwj.skin.fetcher.base.CompoundDrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class DrawableTopFetcher extends CompoundDrawableFetcher<DrawableTopApply> {



    @Override
    public String getAttrName() {
        return "drawableTop";
    }

    @Override
    public DrawableTopApply getSkiApply() {
        return new DrawableTopApply();
    }
}
