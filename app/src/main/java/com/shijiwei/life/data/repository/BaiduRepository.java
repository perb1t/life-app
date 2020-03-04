package com.shijiwei.life.data.repository;

import com.shijiwei.life.data.model.Holiday;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shijiwei on 2020-03-04.
 *
 * @Desc:
 */
public interface BaiduRepository {

    Call<ResponseBody> search(String date);

    Observable<Holiday> query(String date);
}
