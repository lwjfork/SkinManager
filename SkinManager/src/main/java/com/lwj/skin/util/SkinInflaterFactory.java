package com.lwj.skin.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lwj.skin.SkinManager;
import com.lwj.skin.model.SkinAttrItem;
import com.lwj.skin.model.SkinView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class SkinInflaterFactory implements LayoutInflater.Factory2 {


    private ArrayList<SkinView> views = new ArrayList<>();

    private Resources currentSkinRes;
    /**
     * 用于缓存
     */
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new HashMap<>();
    /**
     * 一般 Android 系统的 View 都存储在这几个包下面
     */
    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    /**
     * 系统调用的是两个参数的构造方法，我们也调用这个构造方法
     */
    private static final Class<?>[] mConstructorSignature = new Class[]{
            Context.class, AttributeSet.class};

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }

        return onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = createViewFromSys(name, context, attrs);
        if (view == null) {
            view = createViewFromCustom(name, context, attrs);
        }
        return view;
    }

    // 创建系统自带的View
    private View createViewFromSys(String name, Context context, AttributeSet attrs) {
        View view = null;
        //拼接 name
        for (String aMClassPrefixList : mClassPrefixList) {
            view = createViewFromCustom(aMClassPrefixList + name, context, attrs);
            if (view != null) {
                break;
            }
        }

        return view;
    }

    // 创建 View  完整限定名的View
    private View createViewFromCustom(String name, Context context, AttributeSet attrs) {
        View view = null;
        Constructor<? extends View> constructor = sConstructorMap.get(name);
        //通过反射来获取 View 实例对象
        if (constructor == null) {
            try {
                Class<? extends View> aClass = context.getClassLoader().loadClass(name).asSubclass(View.class);
                constructor = aClass.getConstructor(mConstructorSignature);
                sConstructorMap.put(name, constructor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (constructor != null) {
            try {

                view = constructor.newInstance(context, attrs);
                ArrayList<SkinAttrItem> supportAttrs = ParseViewUtil.parseView(context, attrs);
                if (supportAttrs != null && supportAttrs.size() > 0) {
                    views.add(new SkinView(view, supportAttrs));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }


    // 如果已经设置过的，不要再次设置
    public void apply() {
        if (currentSkinRes == SkinManager.getInstance().getSkinRes()) {
            return;
        }
        for (SkinView view : views) {
            view.apply();
        }
        currentSkinRes = SkinManager.getInstance().getSkinRes();
    }

}
