package com.lwj.skin.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.lwj.skin.SkinManager;
import com.lwj.skin.fetcher.base.ResFetcher;
import com.lwj.skin.model.SkinAttrItem;

import java.util.ArrayList;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class ParseViewUtil {

    /**
     * 无法直接从 AttributeSet 中获取到资源 id 的情况下，需要通过转换的方式来进行获取
     * 比如说，android:background="?attr/colorAccent"
     * 这里 ? 后面拿到值后，还需要去 style.xml 文件中继续获取
     * 对应资源 id，在 style.xml 文件中拿到的才是资源 id
     *
     * @param context 上下文
     * @param attrs   需要获取的资源 id
     * @return 资源 id
     */
    public static int[] getResId(Context context, int[] attrs) {
        int[] resId = new int[attrs.length];
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        for (int i = 0; i < typedArray.length(); i++) {
            resId[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
        return resId;
    }

    public static ArrayList<SkinAttrItem> parseView(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return null;
        }
        if (!SkinManager.getInstance().haveSupportAttr()) {
            return null;
        }
        int attrCount = attrs.getAttributeCount();
        ArrayList<SkinAttrItem> skinAttrItems = new ArrayList<>();
        for (int i = 0; i < attrCount; i++) {
            //获取属性名
            String attributeName = attrs.getAttributeName(i); // 属性名称
            if (SkinManager.getInstance().containAttr(attributeName)) { // 包含支持的属性
                ResFetcher resFetcher = SkinManager.getInstance().getFetcher(attributeName);
                if (resFetcher == null) {
                    continue;
                }
                String attributeValue = attrs.getAttributeValue(i); // 属性值

                // 写死的属性值我们不处理
                if (attributeValue.startsWith("#")) {
                    continue;
                }
                int resID = 0;
                // 引用类型的
                if (attributeValue.startsWith("@")) {
                    resID = Integer.parseInt(attributeValue.substring(1));
                }
                //以？开的的资源，定义在theme的attr属性中
                if (attributeValue.startsWith("?")) {
                    //主题中的属性ID
                    int attrID = Integer.parseInt(attributeValue.substring(1));
                    //实际的资源ID
                    resID = ParseViewUtil.getResId(context, new int[]{attrID})[0];
                }
                if (resID != 0) {
                    SkinAttrItem skinAttrItem = new SkinAttrItem(resID, resFetcher);
                    skinAttrItems.add(skinAttrItem);
                }

            }
        }
        return skinAttrItems;
    }


}
