package com.lwj.skin.fetcher;

import com.lwj.skin.apply.ImageSrcDrawableApply;
import com.lwj.skin.fetcher.base.DrawableFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 * android:src="@drawable/ic_launcher_background"
 */

public class ImageSrcDrawableFetcher extends DrawableFetcher<ImageSrcDrawableApply> {

    @Override
    public String getAttrName() {
        return "src";
    }

    @Override
    public ImageSrcDrawableApply getSkiApply() {
        return new ImageSrcDrawableApply();
    }
}
