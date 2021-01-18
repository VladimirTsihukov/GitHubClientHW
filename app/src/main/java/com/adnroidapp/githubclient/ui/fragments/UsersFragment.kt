package com.adnroidapp.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.model.entity.GitHubUsersRepo
import com.adnroidapp.githubclient.mvp.presenter.UsersPresenter
import com.adnroidapp.githubclient.mvp.view.UsersView
import com.adnroidapp.githubclient.ui.BackButtonListener
import com.adnroidapp.githubclient.ui.adapter.AdapterUsers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var adapterUser: AdapterUsers? = null

    private val presenter by moxyPresenter {
        UsersPresenter(
            GitHubUsersRepo(),
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_user.layoutManager = LinearLayoutManager(context)
        adapterUser = AdapterUsers(presenter.userListPresenter)
        rv_user.adapter = adapterUser
    }

    override fun updateList() {
        adapterUser?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}