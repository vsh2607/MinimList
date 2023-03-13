package com.example.minimlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_THUMB = "extra_thumb"
        const val EXTRA_LANDSCAPE = "extra_landscape"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_GENRE = "extra_genre"
        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_RANKED = "extra_ranked"
        const val EXTRA_POPULARITY = "extra_popularity"
        const val EXTRA_MEMBERS = "extra_members"
        const val EXTRA_SYNOPSIS = "extra_synopsis"
        const val EXTRA_BACKGROUND = "extra_background"
        const val EXTRA_YTLINK = "extra_ytlink"
        const val EXTRA_MLLINK = "extra_mllink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val iv_img_landscape : ImageView = findViewById(R.id.detail_img_land)
        val iv_img_thumbnail : ImageView = findViewById(R.id.detail_img_thumbnail)
        val tv_title : TextView = findViewById(R.id.detail_title)
        val tv_genre : TextView = findViewById(R.id.detail_genre)
        val tv_score : TextView = findViewById(R.id.detail_score)
        val tv_rank : TextView = findViewById(R.id.detail_rank)
        val tv_popularity : TextView = findViewById(R.id.detail_popularity)
        val tv_member : TextView = findViewById(R.id.detail_membership)
        val tv_synopsis : TextView = findViewById(R.id.detail_synopsis)
        val tv_background : TextView = findViewById(R.id.detail_background)
        val wv_trailer : WebView = findViewById(R.id.wv_detail_trailer)
        val fb_action_share : FloatingActionButton = findViewById(R.id.action_share)


        iv_img_landscape.setImageResource(intent.getIntExtra(EXTRA_LANDSCAPE, -1))
        iv_img_thumbnail.setImageResource(intent.getIntExtra(EXTRA_THUMB, -1))
        tv_title.text = intent.getStringExtra(EXTRA_TITLE)
        tv_score.text = intent.getStringExtra(EXTRA_SCORE)
        tv_rank.text = intent.getStringExtra(EXTRA_RANKED)
        tv_popularity.text = intent.getStringExtra(EXTRA_POPULARITY)
        tv_member.text = intent.getStringExtra(EXTRA_MEMBERS)
        tv_synopsis.text = intent.getStringExtra(EXTRA_SYNOPSIS)
        tv_background.text = intent.getStringExtra(EXTRA_BACKGROUND)
        tv_genre.text = intent.getStringExtra(EXTRA_GENRE)
        val value = intent.getStringExtra(EXTRA_MLLINK)

        fb_action_share.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, ("See more in this reference : $value"))
            startActivity(Intent.createChooser(intent, "Send to"))
        }




        wv_trailer.settings.javaScriptEnabled = true

        // Load the HTML content that embeds the YouTube video
        wv_trailer.settings.javaScriptEnabled = true
        wv_trailer.settings.domStorageEnabled
        wv_trailer.loadUrl("https://www.youtube.com/embed/${intent.getStringExtra(EXTRA_YTLINK)}")


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}