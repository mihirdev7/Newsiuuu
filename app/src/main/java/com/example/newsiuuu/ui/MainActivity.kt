package com.example.newsiuuu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsiuuu.R
import com.example.newsiuuu.databinding.ActivityMainBinding
import com.example.newsiuuu.db.ArticleDatabase
import com.example.newsiuuu.repository.NewsRepository

class MainActivity : AppCompatActivity() {
    lateinit var newsViewModel:NewsViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository=NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory=NewsViewModelProviderFactory(application,newsRepository)
        newsViewModel=ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        //setup navigation
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController =navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}