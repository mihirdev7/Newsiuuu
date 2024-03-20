package com.example.newsiuuu.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsiuuu.R
import com.example.newsiuuu.adapters.NewsAdapter
import com.example.newsiuuu.databinding.FragmentFavouriteBinding
import com.example.newsiuuu.ui.MainActivity
import com.example.newsiuuu.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {
    lateinit var  newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding:FragmentFavouriteBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouriteBinding.bind(view)

        newsViewModel=(activity as MainActivity).newsViewModel
        setupFavouritesRecycler()

        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_favouriteFragment_to_articleFragment,bundle)
        }
        val itemTouchHelperCallback=object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=newsAdapter.differ.currentList[position]
                newsViewModel.deleteArticle(article)
                Snackbar.make(view,"Removed from favourites",Snackbar.LENGTH_LONG).apply {
                    setAction("undo"){
                        newsViewModel.addToFavourite(article)
                    }
                    show()
                }
            }
        }
         ItemTouchHelper(itemTouchHelperCallback).apply {
             attachToRecyclerView(binding.recyclerFavourites)
         }

        newsViewModel.getFavouriteNews().observe(viewLifecycleOwner, Observer {articles->
            newsAdapter.differ.submitList(articles)
        })
    }

    private fun setupFavouritesRecycler(){
        newsAdapter= NewsAdapter()
        binding.recyclerFavourites.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)

        }
    }
}