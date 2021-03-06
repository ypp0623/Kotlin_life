package com.example.ypp.life.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.eightbitlab.rxbus.Bus
import com.example.ypp.life.utils.SPUtils
import com.example.ypp.life.utils.StatusBarUtil

/**
 * Created by ypp0623 on 2018/11/15.
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mUserId: String
    protected lateinit var mContext: Context
    protected var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,127)
        mUserId = SPUtils.userId
        mContext = this
        init()
        initBus()

    }

     fun init(){}

     fun initBus(){}

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}