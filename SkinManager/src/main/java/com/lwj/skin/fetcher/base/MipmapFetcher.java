package com.lwj.skin.fetcher.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public abstract class MipmapFetcher implements ResFetcher {

    @Override
    public Drawable getRes(Resources res, int resId) {
        return res.getDrawable(resId);
    }


    @Override
    public String getTypeName() {
        return "mipmap";
    }
}
