package com.shijiwei.life.di.component;

import com.shijiwei.life.app.LifeApplication;
import com.shijiwei.life.di.module.ActivityModule;
import com.shijiwei.life.di.module.AppModule;
import com.shijiwei.life.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,AppModule.class, ActivityModule.class, DataModule.class})
public interface AppComponent {

   void inject(LifeApplication application);

    LifeApplication provideApplication();

}
