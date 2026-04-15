package com.horcrux.svg;

import android.graphics.Rect;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.horcrux.svg.FilterProperties;
import com.horcrux.svg.SVGLength;

/* loaded from: classes3.dex */
public class FilterRegion {
    SVGLength mH;
    SVGLength mW;
    SVGLength mX;
    SVGLength mY;

    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
    }

    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
    }

    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
    }

    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
    }

    private double getRelativeOrDefault(VirtualView virtualView, SVGLength sVGLength, float f, double d) {
        return (sVGLength == null || sVGLength.unit == SVGLength.UnitType.UNKNOWN) ? d : virtualView.relativeOn(sVGLength, f);
    }

    public Rect getCropRect(VirtualView virtualView, FilterProperties.Units units, RectF rectF) {
        double d;
        double relativeOrDefault;
        double d2;
        double d3;
        if (rectF == null) {
            return new Rect(0, 0, 0, 0);
        }
        if (units == FilterProperties.Units.OBJECT_BOUNDING_BOX) {
            d = rectF.left + virtualView.relativeOnFraction(this.mX, rectF.width());
            d3 = rectF.top + virtualView.relativeOnFraction(this.mY, rectF.height());
            d2 = virtualView.relativeOnFraction(this.mW, rectF.width());
            relativeOrDefault = virtualView.relativeOnFraction(this.mH, rectF.height());
        } else {
            float canvasWidth = virtualView.getSvgView().getCanvasWidth();
            float canvasHeight = virtualView.getSvgView().getCanvasHeight();
            double relativeOrDefault2 = getRelativeOrDefault(virtualView, this.mX, canvasWidth, rectF.left);
            double relativeOrDefault3 = getRelativeOrDefault(virtualView, this.mY, canvasHeight, rectF.top);
            double relativeOrDefault4 = getRelativeOrDefault(virtualView, this.mW, canvasWidth, rectF.width());
            d = relativeOrDefault2;
            relativeOrDefault = getRelativeOrDefault(virtualView, this.mH, canvasHeight, rectF.height());
            d2 = relativeOrDefault4;
            d3 = relativeOrDefault3;
        }
        return new Rect((int) d, (int) d3, (int) (d + d2), (int) (d3 + relativeOrDefault));
    }
}
