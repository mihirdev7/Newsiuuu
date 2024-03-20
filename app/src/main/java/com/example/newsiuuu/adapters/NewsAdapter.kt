package com.example.newsiuuu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.resources.R
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsiuuu.models.Article

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
         var articleImage:ImageView=itemView.findViewById(com.example.newsiuuu.R.id.articleImage)
         var articleSource:TextView=itemView.findViewById(com.example.newsiuuu.R.id.articleSource)
         var articleTitle:TextView=itemView.findViewById(com.example.newsiuuu.R.id.articleTitle)
         var articleDescription:TextView=itemView.findViewById(com.example.newsiuuu.R.id.articleDescription)
         var articleDateTime:TextView=itemView.findViewById(com.example.newsiuuu.R.id.articleDateTime)

    }

    private val differCallback=object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }
    val differ=AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(
            com.example.newsiuuu.R.layout.item_news,parent,
            false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article)->Unit)?=null
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]
         holder.itemView.apply {
             Glide.with(this).load(article.urlToImage).into(holder.articleImage)
             holder.articleSource.text= article.source!!.name
             holder.articleTitle.text=article.title
             holder.articleDescription.text=article.description
             holder.articleDateTime.text=article.publishedAt

             setOnClickListener {
                 onItemClickListener?.let {
                     it(article)
                 }
             }
         }
    }
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }

}