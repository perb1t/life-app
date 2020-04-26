package com.shijiwei.life.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shijiwei.life.R;
import com.shijiwei.life.ui.adapter.CarListAdapter;
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

    private CarListAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_car_list;
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    @Override
    public void onInit(@Nullable Bundle savedInstanceState) {

        adapter = new CarListAdapter(this,null);
        mRvCar.setLayoutManager(new LinearLayoutManager(this));
        mRvCar.setAdapter(adapter);
    }

    @Override
    public int getOrientation() {
        return 0;
    }
}
