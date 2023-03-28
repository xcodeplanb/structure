package com.example.structure.util.navi

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions

object HomeNavigationUI {
    fun setupWithNavController(
        item: MenuItem,
        navController: NavController
    ) {
//        onNavDestinationSelected(
//            item,
//            navController
//        )

//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            if (destination.matchDestination(item.itemId)) {
//                item.isChecked = true
//            }
//        }
    }

    private fun NavDestination.matchDestination(@IdRes destId: Int): Boolean =
        hierarchy.any { it.id == destId }
    fun onNavDestinationSelected(item: MenuItem, navController: NavController): Boolean {
        val builder = NavOptions.Builder().setLaunchSingleTop(true).setRestoreState(true)
        if (
            navController.currentDestination!!.parent!!.findNode(item.itemId)
                    is ActivityNavigator.Destination
        ) {
//            builder.setEnterAnim(R.anim.nav_default_enter_anim)
//                .setExitAnim(R.anim.nav_default_exit_anim)
//                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
//                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        } else {
//            builder.setEnterAnim(R.animator.nav_default_enter_anim)
//                .setExitAnim(R.animator.nav_default_exit_anim)
//                .setPopEnterAnim(R.animator.nav_default_pop_enter_anim)
//                .setPopExitAnim(R.animator.nav_default_pop_exit_anim)
        }
        if (item.order and Menu.CATEGORY_SECONDARY == 0) {
            builder.setPopUpTo(
                navController.graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )
        }
        val options = builder.build()
        return try {
            navController.navigate(item.itemId, null, options)
            // Return true only if the destination we've navigated to matches the MenuItem
            navController.currentDestination?.matchDestination(item.itemId) == true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}