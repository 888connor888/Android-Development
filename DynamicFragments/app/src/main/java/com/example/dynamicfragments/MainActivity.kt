package com.example.dynamicfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = Bundle()
        bundle.putString("Key","H3rmit")
        val fragment = FirstFragment()
        fragment.arguments = bundle

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.apply {
            add(fragment)
            add(BlankFragment())
            add(FirstFragment())
        }
        viewPager.adapter = viewPagerAdapter
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container,fragment)
//                .commitNow()
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container,BlankFragment())
//            .commitNow()
    }
}