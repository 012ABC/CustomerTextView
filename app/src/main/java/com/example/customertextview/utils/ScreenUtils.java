package com.example.customertextview.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.TypedValue;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/8 15:22
 */
public class ScreenUtils {

    /**
     * dip转为 px
     */
    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
