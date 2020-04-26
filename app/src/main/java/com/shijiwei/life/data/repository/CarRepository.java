package com.shijiwei.life.data.repository;

import com.shijiwei.life.data.model.Car;

/**
 * Created by shijiwei on 2020-04-26.
 *
 * @Desc:
 */
public interface CarRepository {

    void getCarList(Car.Purpose purpose);
}
