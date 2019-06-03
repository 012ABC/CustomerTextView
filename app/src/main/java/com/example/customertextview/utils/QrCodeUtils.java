package com.example.customertextview.utils;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/22 13:44
 */

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;


/**
 * description 二维码生成工具类
 *
 * @author created by zhanghaiyang
 * @date 2019/4/11 08:59
 */
public class QrCodeUtils {

    /**
     * 黑点颜色
     */
    private static final int BLACK = 0xFF000000;
    /**
     * 白色
     */
    private static final int WHITE = 0xFFFFFFFF;


    /**
     * 生成带LOGO的二维码
     *
     * @param content    要生成二维码的内容
     * @param logoBitmap 二维码中间的logo
     * @param qrWidth    二维码宽度
     * @param qrHeight   二维码高度
     */
    public static Bitmap createCode(String content, Bitmap logoBitmap, int qrWidth, int qrHeight) {
        try {
            int logoWidth = logoBitmap.getWidth();
            int logoHeight = logoBitmap.getHeight();
            int logoHaleWidth = qrWidth / 10;
            int logoHaleHeight = qrHeight / 10;
            // 将logo图片按martix设置的信息缩放
            Matrix m = new Matrix();
            float sx = (float) 2 * logoHaleWidth / logoWidth;
            float sy = (float) 2 * logoHaleHeight / logoHeight;
            // 设置缩放信息
            m.setScale(sx, sy);
            Bitmap newLogoBitmap = Bitmap.createBitmap(logoBitmap, 0, 0, logoWidth,
                    logoHeight, m, false);
            int newLogoWidth = newLogoBitmap.getWidth();
            int newLogoHeight = newLogoBitmap.getHeight();
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置容错级别,H为最高
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置白色边距值
            hints.put(EncodeHintType.MARGIN, 0);
            // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
            BitMatrix matrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);
            matrix = deleteWhite(matrix);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int halfW = width / 2;
            int halfH = height / 2;
            // 二维矩阵转为一维像素数组,也就是一直横着排了
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    /*
                     * 取值范围,可以画图理解下
                     * halfW + newLogoWidth / 2 - (halfW - newLogoWidth / 2) = newLogoWidth
                     * halfH + newLogoHeight / 2 - (halfH - newLogoHeight) = newLogoHeight
                     */
                    if (x > halfW - newLogoWidth / 2 && x < halfW + newLogoWidth / 2
                            && y > halfH - newLogoHeight / 2 && y < halfH + newLogoHeight / 2) {// 该位置用于存放图片信息
                        /*
                         *  记录图片每个像素信息
                         *  halfW - newLogoWidth / 2 < x < halfW + newLogoWidth / 2
                         *  --> 0 < x - halfW + newLogoWidth / 2 < newLogoWidth
                         *   halfH - newLogoHeight / 2  < y < halfH + newLogoHeight / 2
                         *   -->0 < y - halfH + newLogoHeight / 2 < newLogoHeight
                         *   刚好取值newLogoBitmap。getPixel(0-newLogoWidth,0-newLogoHeight);
                         */
                        pixels[y * width + x] = newLogoBitmap.getPixel(
                                x - halfW + newLogoWidth / 2, y - halfH + newLogoHeight / 2);
                    } else {
                        // 设置信息
                        pixels[y * width + x] = matrix.get(x, y) ? BLACK : WHITE;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            // 通过像素数组生成bitmap,具体参考api
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }
}

