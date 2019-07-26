package com.lwj.skin.model;

import android.content.res.Resources;
import android.view.View;

import com.lwj.skin.SkinManager;
import com.lwj.skin.apply.base.SkinApply;
import com.lwj.skin.fetcher.base.ResFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class SkinAttrItem {

    private String entryName; // 资源名称
    private String typeName;  // 资源类型
    private ResFetcher resFetcher;
    private int resId;
    private String attrName;

    public SkinAttrItem(int resId, ResFetcher resFetcher) {
        Resources resources = SkinManager.getInstance().getAppRes();
        this.resId = resId;
        entryName = resources.getResourceEntryName(resId);
        typeName = resources.getResourceTypeName(resId);
        this.resFetcher = resFetcher;
        attrName = resFetcher.getAttrName();
    }

    public String getAttrName() {
        return attrName;
    }

    public ResFetcher getResFetcher() {
        return resFetcher;
    }

    public int getResId() {
        return resId;
    }

    @SuppressWarnings("unchecked")
    public void apply(View view) {
        if (resFetcher == null) { // 找不到对应的资源抓取器
            return;
        }
        SkinApply skinApply = resFetcher.getSkiApply();
        if (skinApply == null) {  // 找不到对应的资源设置器
            return;
        }
        int skinResId = SkinManager.getInstance().getSkinResId(resId, entryName, typeName);
        if (skinResId == 0) {
            return;
        }
        skinApply.apply(view, skinResId, resFetcher);
    }
}
