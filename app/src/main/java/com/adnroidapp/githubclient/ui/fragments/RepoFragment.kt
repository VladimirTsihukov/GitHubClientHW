package com.adnroidapp.githubclient.ui.fragments

import android.os.Bundle
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.presenter.RepoPresenter
import com.adnroidapp.githubclient.mvp.view.RepoView
import com.adnroidapp.githubclient.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_repo.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoFragment: MvpAppCompatFragment(R.layout.fragment_repo), RepoView, BackButtonListener {

    @Inject
    lateinit var router: Router

    val presenter: RepoPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPO_KEY) as GithubRepository
        RepoPresenter(repository, router)
    }

    companion object{
        private const val REPO_KEY = "repo_key"

        fun newInstance(repository: GithubRepository) = RepoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_KEY, repository)
            }
            App.instance.appComponent.inject(this)
        }
    }

    override fun setId(text: String) {
        tv_repo_id.text =
            context?.resources?.let { String.format(it.getString(R.string.repo_id), text) }
    }

    override fun setName(text: String) {
        tv_repo_name.text =
            context?.resources?.let { String.format(it.getString(R.string.repo_name), text) }
    }

    override fun setForksCount(text: String) {
        tv_repo_forksCount.text =
            context?.resources?.let { String.format(it.getString(R.string.repo_forksCount), text) }
    }

    override fun backPressed() = presenter.backPressed()
}