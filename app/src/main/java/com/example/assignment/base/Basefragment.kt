package com.example.assignment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.Webservices.Viewmodelfactorys
import javax.inject.Inject

// TODO: 11/27/20 created by jitendra singh sarv
abstract class Basefragment<DB : ViewDataBinding, VM: ViewModel>(private val mviewmodelclass: Class<VM>): Fragment() {
    @Inject
    lateinit var viewmodelfactorys: Viewmodelfactorys
    lateinit var binding:DB
    lateinit var viewmodel:VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setInjector()
        binding = DataBindingUtil.inflate(inflater,getLayoutResourceId(),container,false)
        viewmodel = ViewModelProvider(this,viewmodelfactorys).get(mviewmodelclass)
        return binding.root
    }
    @LayoutRes
    abstract fun getLayoutResourceId() : Int
    abstract fun setInjector()
    abstract fun initview()
}