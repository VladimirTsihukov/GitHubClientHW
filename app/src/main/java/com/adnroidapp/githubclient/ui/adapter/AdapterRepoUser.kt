package com.adnroidapp.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.presenter.list.IRepoUserListPresenter
import com.adnroidapp.githubclient.mvp.view.list.RepoUserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo_user.view.*

class AdapterRepoUser(val presenter: IRepoUserListPresenter): RecyclerView.Adapter<AdapterRepoUser.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo_user, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
    }

    override fun getItemCount(): Int = presenter.getCount()


    inner class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView),
        LayoutContainer, RepoUserItemView{
        override fun setRepoUser(nameRepo: String) = with(containerView) {
            tv_repo_user.text = String.format(context.resources.getString(R.string.repo), pos + 1, nameRepo)
        }
        override var pos = -1
    }

}