package com.example.assignment.base

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.Webservices.Viewmodelfactorys
import com.example.assignment.util.ContextUtils
import com.example.assignment.util.RealmDialerReference
import java.util.*
import javax.inject.Inject



abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel>(private val mviewmodelclass: Class<VM>) : AppCompatActivity() {
    @Inject
    lateinit var viewmodelfactorys: Viewmodelfactorys
    lateinit var binding: DB
    lateinit var /**/viewmodel: VM

    //    val binding by lazy {
//        DataBindingUtil.setContentView(this, getLayoutResourceId()) as DB
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
//    requestWindowFeature(Window.FEATURE_NO_TITLE)
//    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setInjector()
        if (getLayoutResourceId() != 0)
            binding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        viewmodel = ViewModelProvider(this, viewmodelfactorys).get(mviewmodelclass)
        initview()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    override fun attachBaseContext(newBase: Context?) {
        var languageCode = RealmDialerReference.getInstance(newBase).langCode
        val localeToSwitchTo = Locale(languageCode)
        val localeUpdatedContext: ContextWrapper = ContextUtils.updateLocale(newBase!!, localeToSwitchTo)!!
        super.attachBaseContext(localeUpdatedContext)
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int
    abstract fun setInjector()
    abstract fun initview()
}