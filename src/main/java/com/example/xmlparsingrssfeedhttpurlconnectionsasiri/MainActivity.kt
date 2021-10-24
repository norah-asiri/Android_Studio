package com.example.xmlparsingrssfeedhttpurlconnectionsasiri

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    var topSongs = mutableListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        FetchTopSongs().execute()

    }

    // RSS = https://rss.nytimes.com/services/xml/rss/nyt/MediaandAdvertising.xml
    //online converter = https://rss2json.com/#rss_url=https%3A%2F%2Frss.nytimes.com%2Fservices%2Fxml%2Frss%2Fnyt%2FMediaandAdvertising.xml
    // get url
    private inner class FetchTopSongs : AsyncTask<Void, Void, MutableList<Song>>() {
        val parser = XMLParser()
        override fun doInBackground(vararg params: Void?): MutableList<Song> {
            val url = URL("https://rss.nytimes.com/services/xml/rss/nyt/MediaandAdvertising.xml")
            val urlConnection = url.openConnection() as HttpURLConnection
            topSongs = urlConnection.getInputStream()?.let {
                parser.parse(it)
            }
                    as MutableList<Song>
            return topSongs
        }

        override fun onPostExecute(result: MutableList<Song>?) {
            super.onPostExecute(result)
            val adapter =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, topSongs)
            listView.adapter = adapter
        }

    }
    }