package br.com.cotemig.feedinstagram.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import br.com.cotemig.feedinstagram.R
import br.com.cotemig.feedinstagram.models.Feed
import br.com.cotemig.feedinstagram.services.RetrofitInitializer
import br.com.cotemig.feedinstagram.ui.adapters.FeedAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFeed()
    }

    fun getFeed() {

        var s = RetrofitInitializer().getServiceFeed()
        var call = s.getFeed()

        call.enqueue(object : retrofit2.Callback<List<Feed>> {

            override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {

                if (response.code() == 200) {

                    response.body()?.let {
                        showFeed(it)
                    }

                }

            }

            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {

            }
        })

    }

    fun showFeed(list: List<Feed>) {
        var listFeed = findViewById<ListView>(R.id.listFeed)
        listFeed.adapter = FeedAdapter(this, list)
    }
}