package com.adnroidapp.githubclient.di.modul

import android.widget.ImageView
import com.adnroidapp.githubclient.mvp.model.image.IImageLoader
import com.adnroidapp.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}