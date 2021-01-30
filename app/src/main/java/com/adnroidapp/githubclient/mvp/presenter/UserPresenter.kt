package com.adnroidapp.githubclient.mvp.presenter

import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo
import com.adnroidapp.githubclient.mvp.view.UserView
import com.adnroidapp.githubclient.mvp.presenter.list.IListRepoPresenter
import com.adnroidapp.githubclient.mvp.presenter.list.IRepoUserListPresenter
import com.adnroidapp.githubclient.mvp.view.list.RepoUserItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter (val mainThreadScheduler: Scheduler,
                    private val router: Router,
                     val url: String,
                     val usersRepo: IGithubUsersRepo,
): MvpPresenter<UserView>() {

    val repoUserPresenter = RepoUserListPresenter()

    class RepoUserListPresenter: IRepoUserListPresenter {

        val repoUser = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepoUserItemView) -> Unit)? = null

        override fun bindView(view: RepoUserItemView) {
            val repo = repoUser[view.pos]

            repo.name?.let { view.setRepoUser(it) }
        }

        override fun getCount(): Int = repoUser.size

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRepoAdapter()
        loadRepository(url)

        repoUserPresenter.itemClickListener = {
            repoUserPresenter.repoUser[it.pos].forksCount?.let { forksCount ->
                viewState.showSnackBarForkCount(
                    forksCount
                )
            }
        }
    }

    private fun loadRepository(url: String) {
        usersRepo.getRepositories(url)
            .observeOn(mainThreadScheduler)
            .subscribe({
                repoUserPresenter.repoUser.clear()
                repoUserPresenter.repoUser.addAll(it)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}