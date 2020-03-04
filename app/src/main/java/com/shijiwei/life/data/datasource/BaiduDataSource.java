package com.shijiwei.life.data.datasource;

import com.shijiwei.life.data.api.BaiduApi;
import com.shijiwei.life.data.model.Holiday;
import com.shijiwei.life.data.repository.BaiduRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by shijiwei on 2020-03-04.
 *
 * @Desc:
 */
public class BaiduDataSource implements BaiduRepository {

    private final BaiduApi baiduApi;

    @Inject
    public BaiduDataSource(Retrofit retrofit) {
        baiduApi = retrofit.create(BaiduApi.class);
    }

    @Override
    public Call<ResponseBody> search(String date) {
        return baiduApi.search(date);
    }

    @Override
    public Observable<Holiday> query(String date) {
        return baiduApi.query(date);
    }
}
