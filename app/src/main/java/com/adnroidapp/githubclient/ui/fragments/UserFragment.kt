package com.adnroidapp.githubclient.ui.fragments

import android.os.Bundle
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.presenter.UserPresenter
import com.adnroidapp.githubclient.mvp.view.UserView
import com.adnroidapp.githubclient.ui.BackButtonListener
import com.adnroidapp.githubclient.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView, BackButtonListener {

    private val presenter by moxyPresenter {
        arguments?.getString(MainActivity.KEY_USER_FRAGMENT)?.let {
            UserPresenter(App.instance.router, it)
        }!!
    }

    override fun setNameUser(nameUser: String) {
        user_name.text = nameUser
    }

    companion object {
        fun newInstance(nameUser: String): UserFragment {
            val bundle = Bundle()
            bundle.putString(MainActivity.KEY_USER_FRAGMENT, nameUser)
            val fragment = UserFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}