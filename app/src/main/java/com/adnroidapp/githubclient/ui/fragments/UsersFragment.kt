package com.adnroidapp.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidapp.githubclient.ApiHolder
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.adnroidapp.githubclient.mvp.model.entity.room.DatabaseUser
import com.adnroidapp.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsers
import com.adnroidapp.githubclient.mvp.presenter.UsersPresenter
import com.adnroidapp.githubclient.mvp.view.UsersView
import com.adnroidapp.githubclient.ui.BackButtonListener
import com.adnroidapp.githubclient.ui.adapter.AdapterUsers
import com.adnroidapp.githubclient.ui.image.GlideImageLoader
import com.adnroidapp.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(mainThreadScheduler = AndroidSchedulers.mainThread(),
        usersRepo = RetrofitGithubUsers(ApiHolder().api,
            AndroidNetworkStatus(App.instance), RoomGithubUsersCache(DatabaseUser.getInstance())),
        router = App.instance.router) }

    var adapter: AdapterUsers? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_user.layoutManager = LinearLayoutManager(context)
        adapter = AdapterUsers(presenter.usersListPresenter, GlideImageLoader())
        rv_user.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}