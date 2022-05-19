package br.com.cotemig.feedinstagram.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInitializer {

    val URL = "https://mockup.fluo.app/v1/"

    val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getServiceFeed(): ServiceFeed {
        return retrofit.create(ServiceFeed::class.java)
    }

}