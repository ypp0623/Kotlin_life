package com.example.ypp.life.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ypp.life.Fragment.NewsFragment
import com.example.ypp.life.NewsAdapter
import com.example.ypp.life.R
import com.example.ypp.life.adapter.ScaleTransitionPagerTitleView
import com.example.ypp.life.base.BaseActivity
import com.example.ypp.life.network.entity.NewsEntity
import com.example.ypp.life.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main2.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView

class NewsActivity : BaseActivity() {
    private val newsArray: ArrayList<NewsEntity.ResultBean.DataBean> = ArrayList()
    private lateinit var mAdapter: NewsAdapter
    private val typeArray = arrayOf("top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang")
    private val typeText = arrayOf("头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚")
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,127)
        adapter = MyAdapter(supportFragmentManager)
        mViewPager.adapter = adapter
        initIndicator()
    }

    private fun initIndicator() {
        val commonNavigator = CommonNavigator(mContext)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(mContext)
                simplePagerTitleView.text = typeText[p1]
                simplePagerTitleView.setTextColor(Color.parseColor("#4A4A4A"))
                simplePagerTitleView.textSize = 18f
                simplePagerTitleView.minScale = 1.0f
                simplePagerTitleView.setOnClickListener {
                    mViewPager.currentItem = p1
                    adapter.notifyDataSetChanged()
                }
                return simplePagerTitleView
            }

            override fun getCount(): Int {
                return typeText.size
            }

            override fun getIndicator(p0: Context?): IPagerIndicator? {
                return null
            }
        }
        indicator.navigator = commonNavigator
        ViewPagerHelper.bind(indicator, mViewPager)
    }

    internal inner class MyAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            return NewsFragment.Instance(typeArray[p0])
        }

        override fun getCount(): Int {
            return typeArray.size
        }

    }
}
