package com.example.structure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.structure.data.model.Ad
import com.example.structure.databinding.FragmentHomeBinding
import com.example.structure.databinding.FragmentSplashBinding
import com.example.structure.ui.paging.PagingFragment
import com.example.structure.ui.whether.WhetherFragment
import com.example.structure.util.LogUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAdDataBase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.lifecycleOwner = viewLifecycleOwner
    }

    //open api 개인키를 불러오기 위헤 realtime database를 임시로 사용
    private fun loadAdDataBase() {
        val githubToken =
            FirebaseDatabase.getInstance().reference.child("ad")
        githubToken.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                LogUtil.log("TAG", "it $error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(Ad::class.java)?.let {
                    LogUtil.log("TAG", "it $it")
                    open_weather_app_id = it.open_weather_app_id
                    github_token = it.github_token
                    findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
                }
            }
        })
    }
}