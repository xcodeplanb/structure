package com.example.structure

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
 프래그먼트와 액티비티의 이벤트 전달(공유 ViewModel)
 */
@HiltViewModel
class MainShareViewModel @Inject constructor() : ViewModel() {
}