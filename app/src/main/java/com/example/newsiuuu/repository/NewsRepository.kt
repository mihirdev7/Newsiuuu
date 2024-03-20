package com.example.newsiuuu.repository

import com.example.newsiuuu.api.RetrofitInstance
import com.example.newsiuuu.db.ArticleDatabase
import com.example.newsiuuu.models.Article

class NewsRepository(val db:ArticleDatabase) {

    suspend fun getHeadlines(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article)=db.getArticleDao().upsert(article)

    suspend fun delete(article: Article)=db.getArticleDao().deleteArticle(article)

     fun getFavouriteNews()=db.getArticleDao().getAllArticles()



}