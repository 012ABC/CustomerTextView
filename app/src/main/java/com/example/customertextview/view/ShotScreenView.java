package com.example.customertextview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.customertextview.R;
import com.example.customertextview.utils.QrCodeUtils;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/21 14:16
 */
public class ShotScreenView extends FrameLayout {

    /**
     * 分享标题内容
     */
    private TextView mTitleCotent;

    /**
     * 要分享的二维码
     */
    private ImageView mQrImage;


    private ScrollView mScrollView;

    public ShotScreenView(Context context) {
        this(context, null);
    }

    public ShotScreenView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShotScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.share_layout, this);
        mTitleCotent = view.findViewById(R.id.title_txt);
        mQrImage = view.findViewById(R.id.qr_code_img);
        mScrollView = view.findViewById(R.id.scrollView);
    }

    /**
     * @param text  分享的内容
     * @param qrUrl 分享的标题
     * @param size  二维码尺寸
     * @return 返回截图
     */
    public Bitmap shareBitmapWithQrCode(String text, String qrUrl, int size) {

        Bitmap logoBitmap = null;

        Bitmap qrCodeBitmap;
        if (qrUrl == null) {
            return null;
        }

        logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_share_logo);

        if (logoBitmap==null){
            return null;
        }
        qrCodeBitmap = QrCodeUtils.createCode(qrUrl, logoBitmap, size, size);

        if (qrCodeBitmap == null) {
            return null;
        }

        mTitleCotent.setText(text);
        mQrImage.setImageBitmap(qrCodeBitmap);

        Point outSize = new Point();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getSize(outSize);
        int screenWidth = outSize.x;
        int screenHeight = outSize.y;
        int shareBitmapWidth = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY);
        int shareBitmapHeight = MeasureSpec.makeMeasureSpec(screenHeight, MeasureSpec.EXACTLY);
        measure(shareBitmapWidth, shareBitmapHeight);
        layout(0, 0, screenWidth, screenHeight);

        Bitmap bitmap = getBitmapByView(mScrollView);

        if (!logoBitmap.isRecycled()) {
            logoBitmap.recycle();
        }
        return bitmap;
    }

    /**
     * 截取scrollview的屏幕
     *
     * @param scrollView scrollview
     * @return 返回截图
     */
    private Bitmap getBitmapByView(ScrollView scrollView) {
        int height = 0;
        Bitmap bitmap;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            height += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), height,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

}
