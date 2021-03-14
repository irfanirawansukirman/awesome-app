package com.irfanirawansukirman.core.ui.progress

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.irfanirawansukirman.core.databinding.ProgressDefaultBinding

class ProgressDialogFragment : DialogFragment() {

    private var viewBinding: ProgressDefaultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        if (viewBinding == null) {
            viewBinding = ProgressDefaultBinding.inflate(inflater, container, false)
        }

        return viewBinding?.root
    }

    override fun onDestroy() {
        viewBinding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = ProgressDialogFragment().apply { }
    }
}