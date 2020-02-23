package com.shijiwei.life.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.shijiwei.life.R;

/** Created by shijiwei on 2019-12-03. @Desc: */
public class CornerFrameLayout extends FrameLayout {

  private int radius = 0;

  public CornerFrameLayout(@NonNull Context context) {
    this(context, null);
  }

  public CornerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CornerFrameLayout(
      @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornerFrameLayout);
    radius = typedArray.getDimensionPixelSize(R.styleable.CornerFrameLayout_radius, radius);
    typedArray.recycle();
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    Path path = new Path();
    float[] radii = new float[8];
    for (int i = 0; i < 8; i++) {
      radii[i] = radius;
    }
    path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), radii, Path.Direction.CW);
    canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
    canvas.clipPath(path);
    super.dispatchDraw(canvas);
  }
}
