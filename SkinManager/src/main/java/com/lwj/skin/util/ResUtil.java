package com.lwj.skin.util;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class ResUtil {


    public static Drawable getDrawable(Resources resources, int resId) {
        if(resId == 0){
            return null;
        }
        return resources.getDrawable(resId);
    }

    @ColorInt
    public static int getColor(Resources resources, int resId) {
        if(resId == 0){
            return Color.TRANSPARENT;
        }

        return resources.getColor(resId);
    }
}
