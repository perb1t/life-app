package com.shijiwei.life.di.module;

import com.shijiwei.life.app.LifeApplication;
import com.shijiwei.life.utils.rx.ErrorHandler;
import com.shijiwei.life.utils.rx.RxErrorCallAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class AppModule {

  private LifeApplication application;

  public AppModule(LifeApplication application) {
    RxJavaPlugins.setErrorHandler(new ErrorHandler());
    this.application = application;
  }

  @Provides
  @Singleton
  public LifeApplication provideLifeApplication() {
    return application;
  }


  @Provides
  @Singleton
  public Retrofit provideRetrofit(OkHttpClient okHttpClient){
    return new Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxErrorCallAdapter.create())
            .client(okHttpClient)
            .baseUrl("http://timor.tech/")
            .build();
  }

  @Provides
  @Singleton
  public OkHttpClient provideOkHttpClient(){
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build();
  }
}
