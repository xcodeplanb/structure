package com.example.structure.ui.widzet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.structure.HANGUEL_PATTERN_WITH_CHEONJIIN
import com.example.structure.MainShareViewModel
import com.example.structure.databinding.LayoutTextinputlayoutBinding
import com.example.structure.util.LogUtil
import com.example.structure.util.hideSoftKeyboard
import com.example.structure.util.setEditTextInput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TextInputLayoutFragment : Fragment() {
    private lateinit var binding: LayoutTextinputlayoutBinding
    private val mainShareViewModel: MainShareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutTextinputlayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        with(binding) {
            setEditTextInput(
                editText,
                focusHint,
                editHelper,
                editError,
                clearButton,
                underLine,
                HANGUEL_PATTERN_WITH_CHEONJIIN,
                callBackFocusChange = {

                }, callBackTextChange = {

                }
            )

            editText.setOnEditorActionListener { view, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editText.clearFocus()
                    editText.hideSoftKeyboard()
                    editText.rootView.requestFocus()
                    return@setOnEditorActionListener true
                }
                false
            }
        }


        LogUtil.log("TAG", ": $")
    }

    companion object {
        const val TAG = "WhetherFragment"
    }
}