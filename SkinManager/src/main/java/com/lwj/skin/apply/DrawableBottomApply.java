package com.lwj.skin.apply;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.lwj.skin.apply.base.DrawableApply;
import com.lwj.skin.fetcher.DrawableBottomFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class DrawableBottomApply extends DrawableApply<DrawableBottomFetcher> {

    @Override
    public void apply(View view, Drawable drawable) {
        if (view instanceof TextView) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, null, null, drawable);
        }
    }
}
