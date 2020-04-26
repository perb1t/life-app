package com.shijiwei.life.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.shijiwei.life.R;
import com.shijiwei.life.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by shijiwei on 2020-04-26.
 *
 * @Desc:
 */
public class CarListActivity extends BaseActivity {

    @BindView(R.id.rv_car_list)
    RecyclerView mRvCar;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_car_list;
    }

    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public void onInit(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getOrientation() {
        return 0;
    }
}
