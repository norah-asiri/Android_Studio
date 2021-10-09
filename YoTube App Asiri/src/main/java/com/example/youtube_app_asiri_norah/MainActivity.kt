package com.example.youtube_app_asiri_norah
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private val videos: Array<Array<String>> = arrayOf( // 2d arrray take (name, id of url)
        arrayOf("Recycler view", "UCddGYMQJCo&t=100s"), // here we get id from youtube url a
        arrayOf("lambda", "yx4CY_OUZok"),
        arrayOf("JSON API", "rBQi_7L-Uc8"))

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var player: YouTubePlayer
    private var currentVideo = 0
    private var timeStamp = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // must be add user  for internet, access network state in manifest file
        // check internet connection
        checkInternet()

        youTubePlayerView = findViewById(R.id.ytPlayer)
        youTubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(videos[currentVideo][1], timeStamp)
                initializeRV() // bulid fv
            }
        })

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.exitFullScreen()
        }
    }

    // to make rotate with save value
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("currentVideo", currentVideo)
        outState.putFloat("timeStamp", timeStamp)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentVideo = savedInstanceState.getInt("currentVideo", 0)
        timeStamp = savedInstanceState.getFloat("timeStamp", 0f)
    }

    //rv

    private fun initializeRV(){
        val recyclerView: RecyclerView = findViewById(R.id.rvVideos)
        recyclerView.adapter = VideoAdapter(videos, player)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun checkInternet(){
        if(!connectedToInternet()){
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Internet Connection Not Found")
                .setPositiveButton("RETRY"){_, _ -> checkInternet()}
                .show()
        }
    }

    private fun connectedToInternet(): Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}