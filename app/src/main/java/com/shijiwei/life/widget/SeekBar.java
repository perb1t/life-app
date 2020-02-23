package com.shijiwei.life.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shijiwei.life.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeekBar extends LinearLayout {

  private String label;
  private int labelColor = getResources().getColor(R.color.colorAccent);
  private int labelSize = 14;
  private int max = 100;

  @BindView(R.id.tv_label)
  TextView tvLabel;

  @BindView(R.id.seek_bar)
  android.widget.SeekBar bar;

  private Callback callback;

  public SeekBar(Context context) {
    this(context, null);
  }

  public SeekBar(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    LayoutInflater.from(context).inflate(R.layout.layout_seekbar, this);
    ButterKnife.bind(this);
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SeekBar);
    label = typedArray.getString(R.styleable.SeekBar_label);
    labelColor = typedArray.getColor(R.styleable.SeekBar_labelColor, labelColor);
    labelSize = typedArray.getDimensionPixelSize(R.styleable.SeekBar_labelSize, labelSize);
    max = typedArray.getInt(R.styleable.SeekBar_max, max);
    typedArray.recycle();

    tvLabel.setText(label);
    tvLabel.setTextSize(labelSize);
    tvLabel.setTextColor(labelColor);
    bar.setMax(max);

    bar.setOnSeekBarChangeListener(
        new android.widget.SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(
              android.widget.SeekBar seekBar, int progress, boolean fromUser) {
            if (callback != null) callback.onSelected(SeekBar.this, progress);
          }

          @Override
          public void onStartTrackingTouch(android.widget.SeekBar seekBar) {}

          @Override
          public void onStopTrackingTouch(android.widget.SeekBar seekBar) {}
        });
  }


  public void setCallback(Callback callback) {
    this.callback = callback;
  }

  public void setValue(int val){
    bar.setProgress(val);
  }

  public static interface Callback {
    void onSelected(SeekBar seekBar, int value);
  }
}
