package com.example.customertextview.bean;


import android.graphics.Color;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/16 11:36
 */
public class ColorPoint extends Point {

    private int color;

    public ColorPoint(int x, int y, int color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        if (!(obj instanceof ColorPoint)) {
            return obj.equals(this);
        }
        return super.equals(obj) && ((ColorPoint) obj).color == color;
    }
}
