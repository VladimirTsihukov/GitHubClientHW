package com.adnroidapp.githubclient.ui.image

import android.widget.ImageView
import com.adnroidapp.githubclient.mvp.model.image.IImageLoader
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)

    }
}