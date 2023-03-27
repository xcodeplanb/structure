package com.example.structure.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.structure.R
import com.example.structure.databinding.FragmentHomeBinding
import com.example.structure.util.navi.HomeNavigationUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.event = this

        setUpNavigation()
        setUpObserver()
        setUpClickListener()
        setUpAdapter()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.home_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpClickListener() {

    }

    private fun setUpObserver() {

    }

    private fun setUpAdapter() {

    }

    //For Custom UI
    fun onClickBottomNav(view: View) {
        val popupMenu = PopupMenu(activity, null)
        val menu: Menu = popupMenu.menu
        activity?.menuInflater?.inflate(R.menu.home_menu, menu)
        view.let { view ->
            HomeNavigationUI.onNavDestinationSelected(menu.findItem(view.id), navController)
        }
    }
}
