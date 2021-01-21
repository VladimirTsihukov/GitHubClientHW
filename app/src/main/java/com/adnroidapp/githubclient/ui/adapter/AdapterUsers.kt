package com.adnroidapp.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.presenter.list.IUserListPresenter
import com.adnroidapp.githubclient.mvp.view.list.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*

class AdapterUsers(private val presenter: IUserListPresenter): RecyclerView.Adapter<AdapterUsers.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer, UserItemView {

        override fun setLogin(login: String) = with(containerView) {
            tv_login.text = login
        }

        override var pos = -1
    }
}