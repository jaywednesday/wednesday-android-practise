package com.wednesdaykotlinpractise.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wednesdaykotlinpractise.R
import com.wednesdaykotlinpractise.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vpUserActions.adapter = ViewPagerAdapter(supportFragmentManager)
        tblUserActions.setupWithViewPager(vpUserActions)
    }
}