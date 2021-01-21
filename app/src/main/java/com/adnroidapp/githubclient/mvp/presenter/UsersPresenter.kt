package com.adnroidapp.githubclient.mvp.presenter

import android.util.Log
import io.reactivex.rxjava3.disposables.Disposable
import com.adnroidapp.githubclient.mvp.model.entity.GitHubUsersRepo
import com.adnroidapp.githubclient.mvp.model.entity.GithubUser
import com.adnroidapp.githubclient.mvp.navigation.Screens
import com.adnroidapp.githubclient.mvp.presenter.list.IUserListPresenter
import com.adnroidapp.githubclient.mvp.view.UsersView
import com.adnroidapp.githubclient.mvp.view.list.UserItemView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

const val TAG_RxJava = "TAG_RxJava"

class UsersPresenter(private val usersRepo: GitHubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {
    val userListPresenter = UserListPresenter()
    lateinit var usersDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = {
            router.navigateTo(Screens.UserScreen(userListPresenter.users[it.pos].login))
        }
    }

    private fun loadData() {
        val githubUserList = mutableListOf<GithubUser>()
        usersDisposable = usersRepo.getUsers()
            .subscribe({
                Log.v(TAG_RxJava, it.login)
                githubUserList.add(it)
            }, { e ->
                Log.e(TAG_RxJava, "Error ${e.message}")
            }, {
                Log.v(TAG_RxJava, "onComplete")
                userListPresenter.users.addAll(githubUserList)
                viewState.updateList()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    class UserListPresenter : IUserListPresenter {   //presenter работает с каждым listItems
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount(): Int = users.size
    }

    override fun onDestroy() {
        super.onDestroy()
        usersDisposable.dispose()
    }
}