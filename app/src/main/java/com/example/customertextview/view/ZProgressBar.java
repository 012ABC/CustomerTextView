package com.example.customertextview.view;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.customertextview.R;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.Paint.Align;
import static android.graphics.Paint.FontMetrics;
import static android.graphics.Paint.Style;


/**
 * @author ABC
 */
public class ZProgressBar extends View {


    /**
     * 获取view的宽度
     */
    // private int width;

    /**
     * 获取view的高度
     */
    //  private int height;

    /**
     * 设置shape圆角大小
     */
    private static final int RADIUS = 10;

    /**
     * 获取进度比率
     */
    private float ratio;


    private static final float RATIO_MAX = 1f;

    private static final float RATIO_MIN = 0f;

    private Bitmap mBitmap;

    //private int mBitmapHeight;

//    private int mBitmapWidth;

    private Paint paint;

    private Path path;

    private RectF rect;


    private FontMetrics fontMetrics;

    private String leftTitle;

    private String rightTitle;

    /**
     * 左边颜色
     */
    private String leftColor;

    /**
     * 右边颜色
     */
    private String rightColor;

    /**
     * 左边支持率
     */
    private String leftRatio;

    /**
     * 右边支持率
     */
    private String rightRatio;


    public ZProgressBar(Context context) {
        this(context, null);
    }

    public ZProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {

        paint = new Paint(ANTI_ALIAS_FLAG);

        path = new Path();

        rect = new RectF();

        fontMetrics = new FontMetrics();

        paint.setTextSize(40);

        paint.setColor(Color.WHITE);

        paint.getFontMetrics(fontMetrics);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pk_icon_img);

        Log.i("Dylan121","init方法执行");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("Dylan121","onlayout 方法执行");
    }

    @Override
    protected void onDraw(Canvas canvas) {

Log.i("Dylan121","onDraw");
        super.onDraw(canvas);

        try {
            paint.setColor(Color.parseColor(getLeftColor()));
        } catch (Exception e) {
            paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        }
        rect.set(0, 0, getWidth(), getHeight());

        canvas.drawRoundRect(rect, RADIUS, RADIUS, paint);
        if (getRatio() != RATIO_MIN && getRatio() != RATIO_MAX) {

            try {
                paint.setColor(Color.parseColor(getRightColor()));
            } catch (Exception e) {
                paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }

            float offsetLeft = getWidth() * getRatio();
            float leftMeasure = paint.measureText((getLeftTitle().length() >= getLeftRatio().length()) ?
                            getLeftTitle() : getLeftRatio(),
                    0, (getLeftTitle().length() >= getLeftRatio().length()) ?
                            getLeftTitle().length() : getLeftRatio().length());
            Log.i("Dylan121", "leftMeasure :" + leftMeasure);
            float rightMeasure = paint.measureText((getRightTitle().length() >= getRightRatio().length()) ?
                    getRightTitle() : getRightRatio(), 0, (getRightTitle().length() >= getRightRatio().length()) ?
                    getRightTitle().length() : getRightRatio().length());
            Log.i("Dylan121", "rightMeasure :" + rightMeasure);
            if (offsetLeft <= leftMeasure + mBitmap.getWidth() / 2f + 40) {
                offsetLeft = leftMeasure + mBitmap.getWidth() / 2f + 40;
            } else if (offsetLeft >= getWidth() - rightMeasure - (mBitmap.getWidth() / 2f
                    + 40)) {
                offsetLeft = getWidth() - rightMeasure - (mBitmap.getWidth() / 2f + 40);
            }

            rect.set(offsetLeft, 0, getWidth(), getHeight());

            canvas.drawRoundRect(rect, RADIUS, RADIUS, paint);

            initDrawBitmap(offsetLeft, canvas);

        } else if (RATIO_MIN == getRatio()) {
            try {
                paint.setColor(Color.parseColor(getRightColor()));
            } catch (Exception e) {
                paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            rect.set(0, 0, getWidth(), getHeight());
            canvas.drawRoundRect(rect, RADIUS, RADIUS, paint);
        }
        drawContent(canvas);

        float leftMeasure = paint.measureText((getLeftTitle().length() >= getLeftRatio().length()) ?
                        getLeftTitle() : getLeftRatio(),
                0, (getLeftTitle().length() >= getLeftRatio().length()) ?
                        getLeftTitle().length() : getLeftRatio().length());
        Log.i("Dylan121", "leftMeasure :" + leftMeasure);
        float rightMeasure = paint.measureText((getRightTitle().length() >= getRightRatio().length()) ?
                getRightTitle() : getRightRatio(), 0, (getRightTitle().length() >= getRightRatio().length()) ?
                getRightTitle().length() : getRightRatio().length());
        Log.i("Dylan121", "rightMeasure :" + rightMeasure);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void drawContent(Canvas canvas) {


        paint.setTextAlign(Align.LEFT);

        canvas.drawText(getLeftTitle(), 20, getHeight() / 2f -
                (fontMetrics.descent - fontMetrics.ascent) / 2f + 4, paint);

        canvas.drawText(getLeftRatio(),
                20, getHeight() - (fontMetrics.descent - fontMetrics.ascent) / 2f - 4, paint);

        paint.setTextAlign(Align.RIGHT);
        canvas.drawText(getRightTitle(), getWidth() - 20, getHeight() / 2f - (fontMetrics.descent -
                fontMetrics.ascent) / 2f + 4, paint);

        canvas.drawText(getRightRatio(), getWidth() - 20, getHeight() - (fontMetrics.descent - fontMetrics.ascent) / 2f
                - 4, paint);

    }

    /**
     * 绘制平行四边形和PK图片
     *
     * @param offsetLeft 距离左边绘制起点
     * @param canvas     画布
     */
    private void initDrawBitmap(float offsetLeft, Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        path.moveTo(offsetLeft, 0);
        path.lineTo(offsetLeft + 30, 0);
        path.lineTo(offsetLeft + 10, getHeight());
        path.lineTo(offsetLeft - 20, getHeight());
        path.close();

        canvas.drawPath(path, paint);

        path.reset();

        canvas.drawBitmap(mBitmap, (offsetLeft + 5) - mBitmap.getWidth() / 2f, (getHeight() - mBitmap.getHeight()) / 2f,
                paint);
    }

    public String getLeftColor() {
        return leftColor;
    }

    public void setLeftColor(String leftColor) {
        this.leftColor = leftColor;
    }

    public String getRightColor() {
        return rightColor;
    }

    public void setRightColor(String rightColor) {
        this.rightColor = rightColor;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }

    public String getLeftRatio() {
        return leftRatio;
    }

    public void setLeftRatio(String leftRatio) {
        this.leftRatio = leftRatio;
    }

    public String getRightRatio() {
        return rightRatio;
    }

    public void setRightRatio(String rightRatio) {
        this.rightRatio = rightRatio;
    }
}