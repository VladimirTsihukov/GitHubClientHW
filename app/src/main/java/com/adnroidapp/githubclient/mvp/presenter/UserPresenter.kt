package com.adnroidapp.githubclient.mvp.presenter

import com.adnroidapp.githubclient.mvp.model.entity.GithubRepository
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.model.repo.IGithubUsersRepo
import com.adnroidapp.githubclient.mvp.navigation.Screens
import com.adnroidapp.githubclient.mvp.view.UserView
import com.adnroidapp.githubclient.mvp.presenter.list.IRepoUserListPresenter
import com.adnroidapp.githubclient.mvp.view.list.RepoUserItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter (val user: GithubUser): MvpPresenter<UserView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var usersRepo: IGithubUsersRepo

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
        loadRepository(user)

        repoUserPresenter.itemClickListener = {
            val repo = repoUserPresenter.repoUser[it.pos]
            router.navigateTo(Screens.RepoScreen(repo))
        }
    }

    private fun loadRepository(user: GithubUser) {
        usersRepo.getRepositories(user)
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

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}