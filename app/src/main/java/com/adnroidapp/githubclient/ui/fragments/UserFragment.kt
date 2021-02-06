package com.adnroidapp.githubclient.ui.fragments

import android.os.Bundle
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.di.repository.RepoSubComponent
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.presenter.UserPresenter
import com.adnroidapp.githubclient.mvp.view.UserView
import com.adnroidapp.githubclient.ui.BackButtonListener
import com.adnroidapp.githubclient.ui.MainActivity
import com.adnroidapp.githubclient.ui.adapter.AdapterRepoUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView, BackButtonListener {

    private var repositorySubComponent: RepoSubComponent? = null

    private val presenter by moxyPresenter {
        repositorySubComponent = App.instance.initRepoSubComponent()
        arguments?.getParcelable<GithubUser>(MainActivity.KEY_USER_FRAGMENT_URL)?.let {
            UserPresenter(user = it).apply {
                repositorySubComponent?.inject(this)
            }
        }!!
    }

    var adapter: AdapterRepoUser? = null

    override fun initRepoAdapter() {
        adapter = AdapterRepoUser(presenter.repoUserPresenter)
        rv_repo_user.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showSnackBarForkCount(forkCount: Int) {
        val textForkCount =
            context?.resources?.getString(R.string.forks_count)
                ?.let { String.format(it, forkCount) }
        textForkCount?.let { text ->
            view?.let { view ->
                Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun release() {
        repositorySubComponent = null
        App.instance.releaseRepoSubComponent()
    }

    companion object {
        fun newInstance(user: GithubUser): UserFragment {
            val bundle = Bundle()
            bundle.putParcelable(MainActivity.KEY_USER_FRAGMENT_URL, user)
            val fragment = UserFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}