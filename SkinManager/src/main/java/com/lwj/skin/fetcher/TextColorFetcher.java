package com.lwj.skin.fetcher;

import com.lwj.skin.apply.TextColorApply;
import com.lwj.skin.fetcher.base.ColorFetcher;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class TextColorFetcher extends ColorFetcher<TextColorApply> {


    @Override
    public String getAttrName() {
        return "textColor";
    }


    @Override
    public TextColorApply getSkiApply() {
        return new TextColorApply();
    }
}
