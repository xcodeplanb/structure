package com.example.structure.util.navi

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.customview.widget.Openable
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUiSaveStateControl
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.example.structure.R
import com.example.structure.util.LogUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigationrail.NavigationRailView
import java.lang.ref.WeakReference

/*//하단 바텀네비게이션 ui 변경에 용이하도록 navController만 연결 (진행중)
//    fun onClickBottomNav(view: View) {
//        val popupMenu = PopupMenu(activity, null)
//        val menu: Menu = popupMenu.menu
//        activity?.menuInflater?.inflate(R.menu.home_menu, menu)
//        HomeNavigationUI.changeNavDestination(menu.findItem(view.id), navController)
//    }*/

object HomeNavigationUI {

}