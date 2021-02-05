package com.adnroidapp.githubclient.ui

import android.os.Bundle
import com.adnroidapp.githubclient.App
import com.adnroidapp.githubclient.R
import com.adnroidapp.githubclient.mvp.presenter.MainPresenter
import com.adnroidapp.githubclient.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.container) //осуществляет навигацию

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }

    companion object {
        const val KEY_USER_FRAGMENT_URL = "KEY_USER_FRAGMENT_URL"
    }
}