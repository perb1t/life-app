package com.shijiwei.life.di.module;

import com.shijiwei.life.data.datasource.BaiduDataSource;
import com.shijiwei.life.data.repository.BaiduRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shijiwei on 2020-03-04.
 *
 * @Desc:
 */
@Module
public class DataModule {

    @Provides
    BaiduRepository provideBaiduRepository(BaiduDataSource dataSource){
        return dataSource;
    }
}
