package com.hefny.hady.gadphasetwoproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.hefny.hady.gadphasetwoproject.ui.main.SectionsPagerAdapter
import com.hefny.hady.gadphasetwoproject.ui.main.SharedViewModel
import com.hefny.hady.gadphasetwoproject.utils.Resource

class MainActivity : AppCompatActivity() {
    private lateinit var sharedViewModel: SharedViewModel
    private val TAG = "AppDebug"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        sharedViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(SharedViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sharedViewModel.learningLeadersLiveData.observe(this, Observer { dataResource ->
            when (dataResource) {
                is Resource.Loading -> {
                    Log.d(TAG, "subscribeObservers: LearningLeaders: LOADING")
                }
                is Resource.Success -> {
                    Log.d(TAG, "subscribeObservers: LearningLeaders SUCCESS")
                    Log.d(TAG, "subscribeObservers: learningLeadersList: ${dataResource.data}")
                }
                is Resource.Error -> {
                    Log.d(TAG, "subscribeObservers: LearningLeaders ERROR")
                    Log.d(TAG, "subscribeObservers: LearningLeaders errorMessage: ${dataResource.message}")
                }
            }
        })

        sharedViewModel.skillIqLeadersLiveData.observe(this, Observer { dataResource ->
            when (dataResource) {
                is Resource.Loading -> {
                    Log.d(TAG, "subscribeObservers: SkillIqLeaders: LOADING")
                }
                is Resource.Success -> {
                    Log.d(TAG, "subscribeObservers: SkillIqLeaders: SUCCESS")
                    Log.d(TAG, "subscribeObservers: skillIqLeadersList: ${dataResource.data}")
                }
                is Resource.Error -> {
                    Log.d(TAG, "subscribeObservers: SkillIqLeaders: ERROR")
                    Log.d(TAG, "subscribeObservers: SkillIqLeaders: errorMessage: ${dataResource.message}")
                }
            }
        })
    }
}