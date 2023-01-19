//package com.smallticket.petping
//
//import com.smallticket.petping.R
//import com.smallticket.petping.AppConstants.SYSTEM_CHECKING_INFO
//import com.smallticket.petping.AppConstants.closeMissionAlert
//import com.smallticket.petping.AppConstants.closeMissionPetAlert
//import com.smallticket.petping.data.vo.DeepLink
//import com.smallticket.petping.data.vo.EventBus.NetworkErrorEvent
//import com.smallticket.petping.data.vo.EventBus.ServerErrorEvent
//import com.smallticket.petping.databinding.FragmentMainBinding
//import com.smallticket.petping.firebase.RemoteConfigMangaer
//import com.smallticket.petping.ui.common.dialog.DoubleBtnDialog
//import com.smallticket.petping.ui.common.dialog.SingleBtnDialog
//import com.smallticket.petping.ui.common.widget.CustomSnackBar
//import com.example.structure.util.LogUtil
//import com.smallticket.petping.util.SharedPreferencesManager
//import com.smallticket.petping.util.getTelephoneNetworkName
//import com.smallticket.petping.util.repeatOnStarted
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.OnBackPressedCallback
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import androidx.fragment.app.viewModels
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.findNavController
//import dagger.hilt.android.AndroidEntryPoint
//import org.greenrobot.eventbus.EventBus
//import org.json.JSONObject
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class MainFragment : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentMainBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = viewLifecycleOwner
//        binding.viewModel = viewModel
//
//        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        viewModel.checkAppVersion()
//    }
//}
