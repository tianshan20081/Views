package com.aoeng.views.views;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by sczhang on 15/5/15.
 */
public class CircleImageDrawable extends Drawable {
    /**
     * Draw in its bounds (set via setBounds) respecting optional effects such
     * as alpha (set via setAlpha) and color filter (set via setColorFilter).
     *
     * @param canvas The canvas to draw into
     */
    @Override
    public void draw(Canvas canvas) {

    }

    /**
     * Specify an alpha value for the drawable. 0 means fully transparent, and
     * 255 means fully opaque.
     *
     * @param alpha
     */
    @Override
    public void setAlpha(int alpha) {

    }

    /**
     * Specify an optional color filter for the drawable. Pass {@code null} to
     * remove any existing color filter.
     *
     * @param cf the color filter to apply, or {@code null} to remove the
     *           existing color filter
     */
    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    /**
     * Return the opacity/transparency of this Drawable.  The returned value is
     * one of the abstract format constants in
     * {@link PixelFormat}:
     * {@link PixelFormat#UNKNOWN},
     * {@link PixelFormat#TRANSLUCENT},
     * {@link PixelFormat#TRANSPARENT}, or
     * {@link PixelFormat#OPAQUE}.
     * <p/>
     * <p>Generally a Drawable should be as conservative as possible with the
     * value it returns.  For example, if it contains multiple child drawables
     * and only shows one of them at a time, if only one of the children is
     * TRANSLUCENT and the others are OPAQUE then TRANSLUCENT should be
     * returned.  You can use the method {@link #resolveOpacity} to perform a
     * standard reduction of two opacities to the appropriate single output.
     * <p/>
     * <p>Note that the returned value does <em>not</em> take into account a
     * custom alpha or color filter that has been applied by the client through
     * the {@link #setAlpha} or {@link #setColorFilter} methods.
     *
     * @return int The opacity class of the Drawable.
     * @see PixelFormat
     */
    @Override
    public int getOpacity() {
        return 0;
    }
}
