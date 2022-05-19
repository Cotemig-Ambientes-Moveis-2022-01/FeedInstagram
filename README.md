# FeedInstagram

# 1 - Adicionar as dependências no gradle

```
    // biblioteca do retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    // biblioteca do GSON - responsável por transformar JSON em Objeto
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // biblioteca de download de imagens (url)
    implementation 'io.coil-kt:coil:1.2.0'
```

# 2 - Implementar models de acordo com o JSON

```kotlin
class Feed {
    var description: String = ""
    var user: String = ""
    var image: String = ""
}
```

# 3 - Implementar Services

```kotlin
interface ServiceFeed {

    @GET("feed")
    fun getFeed(): Call<List<Feed>>

}
```

# 4 - Implementar RetrofitInitializer

```kotlin
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
```

# 5 - Adicionar LisView no XML da Activity

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"
    tools:context=".ui.activities.MainActivity">

    <ListView
        android:id="@+id/listFeed"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:divider="@color/transparent" />

</RelativeLayout>
```

# 6 - Implementar XML do Item

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/user"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp" />

    <ImageView
        android:id="@+id/image"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:scaleType="centerCrop" />

    <TextView
        android:id="@+id/description"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp" />

</LinearLayout>
```

# 7 - Implementar o Adapter

```kotlin
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
```

# 8 - Implementar a chamada da API na Activity

```kotlin
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
```

# 9 - Adicionar o Uses Permission

```xml
    <uses-permission android:name="android.permission.INTERNET" />
```

