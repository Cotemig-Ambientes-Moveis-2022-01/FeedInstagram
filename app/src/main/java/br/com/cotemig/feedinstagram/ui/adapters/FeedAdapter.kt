package br.com.cotemig.feedinstagram.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.cotemig.feedinstagram.R
import br.com.cotemig.feedinstagram.models.Feed
import coil.load

class FeedAdapter(var context: Context, var list: List<Feed>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p: Int, p1: View?, p2: ViewGroup?): View {

        var view = LayoutInflater.from(context).inflate(R.layout.item_feed, null)

        var user = view.findViewById<TextView>(R.id.user)
        user.text = list[p].user

        var image = view.findViewById<ImageView>(R.id.image)
        image.load(list[p].image)

        var description = view.findViewById<TextView>(R.id.description)
        description.text = list[p].description

        return view
    }
}