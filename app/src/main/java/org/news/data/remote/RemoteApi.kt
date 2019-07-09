package org.news.data.remote

import io.reactivex.Observable
import org.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("/v2/top-headlines")
    fun getArticles(@Query("country") country: String, @Query("apiKey") apiKey: String): Observable<NewsResponse>
}