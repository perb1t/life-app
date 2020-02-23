package com.shijiwei.life.di.module;

import com.shijiwei.life.app.LifeApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  private LifeApplication application;

  public AppModule(LifeApplication application) {
    this.application = application;
  }

  @Provides
  public LifeApplication provideLifeApplication() {
    return application;
  }
}
