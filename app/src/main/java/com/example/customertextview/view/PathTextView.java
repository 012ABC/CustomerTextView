package com.example.customertextview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/28 08:50
 */
public class PathTextView extends View {

    public static final String CONTENT_STRING="一壶浊酒喜相逢";

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path mPath = new Path();

    public PathTextView(Context context) {
        this(context, null);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    //    canvas.drawTextOnPath(CONTENT_STRING,0,CONTENT_STRING.length(),mPath,);
    }
}
