package com.irfanirawansukirman.core.base

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.irfanirawansukirman.core.ui.progress.ProgressDialogFragment
import com.irfanirawansukirman.core.util.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : BaseVM>(
    private val viewBinder: (LayoutInflater) -> ViewBinding
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var progress: ProgressDialogFragment? = null

    val viewBinding by lazy { viewBinder.invoke(LayoutInflater.from(requireContext())) as VB }
    val viewModel: VM by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(getViewModel())
    }
    val parentActivity by lazy { (requireActivity() as BaseActivity) }
    val fragmentContext by lazy { requireContext() }

    private var isViewCreated = false
    private var isComponentCreated = false

    abstract fun onLoadVM(viewModel: VM)
    abstract fun getViewModel(): Class<VM>
    abstract fun initInjector()
    abstract fun initComponent()
    abstract fun initViewListener()
    abstract fun onViewReady(savedInstanceState: Bundle?, view: View)
    abstract fun onClear()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        if (!isComponentCreated) {
            initComponent()
            isComponentCreated = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isViewCreated) {
            onViewReady(savedInstanceState, view)
            initProgressDialog()
            initViewListener()
            onLoadVM(viewModel)
            isViewCreated = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onClear()
    }

    fun getApplication(): Application = parentActivity.application

    private fun initProgressDialog() {
        if (progress == null) {
            progress = ProgressDialogFragment.newInstance()
            progress?.isCancelable = false
        }
    }

    fun showProgress() {
        progress?.show(parentActivity.supportFragmentManager, progress?.tag)
    }

    fun hideProgress() {
        try {
            progress?.dismiss()
        } catch (e: Exception) {
            val prev = parentActivity.supportFragmentManager.findFragmentByTag(progress?.tag)
            if (prev != null) {
                val df = prev as DialogFragment
                df.dismiss()
            }
        }
    }
}