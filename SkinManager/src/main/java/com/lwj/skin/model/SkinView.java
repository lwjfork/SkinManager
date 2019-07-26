package com.lwj.skin.model;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.lwj.skin.SkinManager;
import com.lwj.skin.fetcher.base.DrawableFetcher;

import java.util.ArrayList;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class SkinView {

    private View view;
    private ArrayList<SkinAttrItem> skinAttrItem = new ArrayList<>();

    public SkinView(View view, ArrayList<SkinAttrItem> skinAttrItem) {
        this.view = view;
        this.skinAttrItem = skinAttrItem;
    }

    public void apply() {
        Drawable drawableLeft = null;
        Drawable drawableTop = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;
        DrawableFetcher drawableFetcher = null;
        for (SkinAttrItem attrItem : skinAttrItem) {
            String attrName = attrItem.getAttrName();
            switch (attrName) {
                case "drawableLeft":
                    drawableFetcher = (DrawableFetcher) attrItem.getResFetcher();
                    drawableLeft = drawableFetcher.getRes(SkinManager.getInstance().getSkinRes(), attrItem.getResId());
                    break;
                case "drawableTop":
                    drawableFetcher = (DrawableFetcher) attrItem.getResFetcher();
                    drawableTop = drawableFetcher.getRes(SkinManager.getInstance().getSkinRes(), attrItem.getResId());
                    break;
                case "drawableRight":
                    drawableFetcher = (DrawableFetcher) attrItem.getResFetcher();
                    drawableRight = drawableFetcher.getRes(SkinManager.getInstance().getSkinRes(), attrItem.getResId());
                    break;
                case "drawableBottom":
                    drawableFetcher = (DrawableFetcher) attrItem.getResFetcher();
                    drawableBottom = drawableFetcher.getRes(SkinManager.getInstance().getSkinRes(), attrItem.getResId());
                    break;
                default:
                    attrItem.apply(view);
            }
        }

        if (drawableLeft != null || drawableTop != null || drawableRight != null || drawableBottom != null) {
            TextView tv = (TextView) view;
            tv.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }

    }
}
