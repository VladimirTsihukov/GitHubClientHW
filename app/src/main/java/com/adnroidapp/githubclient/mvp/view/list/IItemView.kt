package com.adnroidapp.githubclient.mvp.view.list

import moxy.MvpView

interface IItemView : MvpView {     //для каждого item viewHolder
    var pos : Int
}