package com.shijiwei.life.di.module;

import com.shijiwei.life.ui.activity.ListWidgetActivity;
import com.shijiwei.life.ui.activity.MainActivity;
import com.shijiwei.life.ui.activity.TtsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract TtsActivity bindTtsActivity();

    @ContributesAndroidInjector
    abstract ListWidgetActivity bindListWidgetActivity();
}
