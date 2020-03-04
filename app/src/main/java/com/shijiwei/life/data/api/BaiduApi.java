package com.shijiwei.life.data.api;

import com.shijiwei.life.data.model.BadiduSerchanResp;
import com.shijiwei.life.data.model.Holiday;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shijiwei on 2020-03-03.
 *
 * @Desc:
 */
public interface BaiduApi {

    @GET("/api/holiday/info/{date}")
    Call<ResponseBody> search(@Path("date") String date);

    @GET("/api/holiday/info/{date}")
    Observable<Holiday> query(@Path("date") String date);
}
