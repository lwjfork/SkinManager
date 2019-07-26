package com.lwj.skin.apply;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.lwj.skin.apply.base.DrawableApply;
import com.lwj.skin.fetcher.ImageSrcDrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class ImageSrcDrawableApply extends DrawableApply<ImageSrcDrawableFetcher> {

    @Override
    public void apply(View view, Drawable drawable) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageDrawable(drawable);
        }
    }
}
