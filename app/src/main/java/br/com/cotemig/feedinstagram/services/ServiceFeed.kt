package br.com.cotemig.feedinstagram.services

import br.com.cotemig.feedinstagram.models.Feed
import retrofit2.Call
import retrofit2.http.GET

interface ServiceFeed {

    @GET("feed")
    fun getFeed(): Call<List<Feed>>

}