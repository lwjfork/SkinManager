package com.lwj.skin.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class ResUtil {


    public static Drawable getDrawable(Resources resources, int resId) {
        return resources.getDrawable(resId);
    }

    @ColorInt
    public static int getColor(Resources resources, int resId) {
        return resources.getColor(resId);
    }
}
