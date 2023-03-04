package com.example.structure.util

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.structure.R
import java.util.regex.Pattern

fun setEditTextInput(
    editText: EditText,
    focusHintTextView: TextView,
    helperTextView: TextView,
    errorTextView: TextView,
    clearImageView: ImageView,
    underLineView: View,
    patternString: String,
    callBackFocusChange: (hasFocus: Boolean) -> Unit,
    callBackTextChange: (text: String) -> Unit
) {
    var mText = ""
    var mFocus = false
    val mHint = editText.hint

    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            mText = p0?.toString() ?: ""
            if (mFocus) {
                if (mText.isNotEmpty()) {
                    clearImageView.visibility = View.VISIBLE
                } else {
                    clearImageView.visibility = View.INVISIBLE
                }

                if (mText.isEmpty() || Pattern.compile(patternString).matcher(mText).matches()) {
                    helperTextView.visibility = View.VISIBLE
                    errorTextView.visibility = View.INVISIBLE
                } else {
                    helperTextView.visibility = View.INVISIBLE
                    errorTextView.visibility = View.VISIBLE
                }
            }
            callBackTextChange(mText)
        }
    })

    editText.setOnFocusChangeListener { _, focus ->
        mFocus = focus

        if (mFocus) {
            if (mText.isNotEmpty()) {
                clearImageView.visibility = View.VISIBLE
            }

            focusHintTextView.visibility = View.VISIBLE
            editText.hint = ""

            if (mText.isEmpty() || Pattern.compile(patternString).matcher(mText).matches()) {
                helperTextView.visibility = View.VISIBLE
            }

            underLineView.setBackgroundResource(R.color.color_aaaaaa)
        } else {
            if (mText.isEmpty()) {
                focusHintTextView.visibility = View.INVISIBLE
                editText.hint = mHint
            }

            clearImageView.visibility = View.INVISIBLE
            helperTextView.visibility = View.INVISIBLE
            underLineView.setBackgroundResource(R.color.color_dddddd)
        }
        callBackFocusChange(mFocus)
    }
}