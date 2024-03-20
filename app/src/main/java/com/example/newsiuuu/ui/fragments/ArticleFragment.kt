package com.example.newsiuuu.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsiuuu.R
import com.example.newsiuuu.databinding.ActivityMainBinding
import com.example.newsiuuu.databinding.FragmentArticleBinding
import com.example.newsiuuu.ui.MainActivity
import com.example.newsiuuu.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var newsViewModel: NewsViewModel
    val args:ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentArticleBinding.bind(view)

        newsViewModel=(activity as MainActivity).newsViewModel
        val article=args.article


        binding.webView.apply {
            webViewClient= WebViewClient()
            article.url.let {
                loadUrl(it!!)
            }
        }
        binding.fab.setOnClickListener {
            newsViewModel.addToFavourite(article)
          Snackbar.make(view,"Added to favourites",Snackbar.LENGTH_SHORT).show()
        }
    }

}