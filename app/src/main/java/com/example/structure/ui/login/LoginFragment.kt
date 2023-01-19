//package com.smallticket.petping.ui.login
//
//import com.smallticket.petping.*
//import com.smallticket.petping.data.vo.AgreementConfig
//import com.smallticket.petping.data.vo.AppleLoginConfig
//import com.smallticket.petping.data.vo.EventBus.CheckNotificationPermissionEvent
//import com.smallticket.petping.data.vo.NaverData
//import com.smallticket.petping.databinding.FragmentLoginBinding
//import com.example.structure.ui.base.BaseFragment
//import com.smallticket.petping.ui.common.dialog.PermissionDialog
//import com.smallticket.petping.ui.common.dialog.SingleBtnDialog
//import com.smallticket.petping.ui.common.widget.CustomSnackBar
//import com.example.structure.util.LogUtil
//import com.smallticket.petping.util.SharedPreferencesManager
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.content.ContextWrapper
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.OnBackPressedCallback
//import androidx.fragment.app.activityViewModels
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.LifecycleService
//import androidx.navigation.findNavController
//import com.example.structure.ui.login.LoginViewModel
//import com.kakao.sdk.auth.LoginClient
//import com.kakao.sdk.auth.model.OAuthToken
//import com.kakao.sdk.user.UserApiClient
//import com.navercorp.nid.NaverIdLoginSDK
//import com.navercorp.nid.oauth.NidOAuthBehavior
//import com.navercorp.nid.oauth.NidOAuthLogin
//import com.navercorp.nid.oauth.OAuthLoginCallback
//import com.navercorp.nid.profile.NidProfileCallback
//import com.navercorp.nid.profile.data.NidProfileResponse
//import dagger.hilt.android.AndroidEntryPoint
//import org.greenrobot.eventbus.EventBus
//import javax.inject.Inject
//
///**
// * android-petping-2
// * Class: LoginFragment
// * Created by cliff on 2022/03/10.
// *
// * Description:
// */
//@AndroidEntryPoint
//class LoginFragment : BaseFragment<FragmentLoginBinding>() {
//
//    override fun inflateViewBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ): FragmentLoginBinding {
//        return FragmentLoginBinding.inflate(inflater)
//    }
//
//    private val viewModel: LoginViewModel by viewModels()
//    private val mainShareViewModel: MainShareViewModel by activityViewModels()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//
//        //로그인 화면 진입시 딥링크 이벤트 수신 홈에서 동작하지 않도록
//        mainShareViewModel.moveLinkedScreen.observeEvent(viewLifecycleOwner, {
//
//        })
//
//        with(viewModel) {
//
//        }
//    }
//
//    override fun onDestroyView() {
//        onBackPressedCallback.remove()
//        super.onDestroyView()
//    }
//}