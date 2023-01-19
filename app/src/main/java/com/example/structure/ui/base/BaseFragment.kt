package com.example.structure.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding



abstract class BaseFragment<VB : ViewBinding>() : Fragment() {
    var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater,container)
        return requireNotNull(_binding).root
    }
}