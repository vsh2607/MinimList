package com.example.minimlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvAnimes :RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimes = findViewById(R.id.rv_anime)
        rvAnimes.setHasFixedSize(true)

        list.addAll(getListAnimes())
        showRecyclerList()
    }

    private fun getListAnimes() : ArrayList<Anime>{
        val dataImgThumb = resources.obtainTypedArray(R.array.data_img_thumbnail)
        val dataImgLand = resources.obtainTypedArray(R.array.data_img_land)
        val dataSynopsis = resources.getStringArray(R.array.data_sysnopsis)
        val dataScore = resources.getStringArray(R.array.data_score)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataRanked = resources.getStringArray(R.array.data_rank)
        val dataPopularity = resources.getStringArray(R.array.data_popularity)
        val dataMembers = resources.getStringArray(R.array.data_members)
        val dataBackground = resources.getStringArray(R.array.data_background)
        val dataYtLink = resources.getStringArray(R.array.data_yt_link)
        val dataMlLink = resources.getStringArray(R.array.data_ml_link)

        val listAnime = ArrayList<Anime>()

        for(i in dataTitle.indices){
                val anime = Anime(dataImgThumb.getResourceId(i, -1), dataImgLand.getResourceId(i, -1),
                dataTitle[i], dataGenre[i], dataScore[i], dataRanked[i], dataPopularity[i], dataMembers[i], dataSynopsis[i],
                dataBackground[i], dataYtLink[i], dataMlLink[i])
                listAnime.add(anime)
        }
        return listAnime
    }


    private fun showSelectedAnime(anime: Anime) {
            val detailIntent = Intent(this, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_THUMB, anime.image_thumbnail)
            detailIntent.putExtra(DetailActivity.EXTRA_LANDSCAPE, anime.image_landscape)
            detailIntent.putExtra(DetailActivity.EXTRA_TITLE, anime.title)
            detailIntent.putExtra(DetailActivity.EXTRA_GENRE, anime.genre)
            detailIntent.putExtra(DetailActivity.EXTRA_SCORE, anime.score)
            detailIntent.putExtra(DetailActivity.EXTRA_RANKED, anime.ranked)
            detailIntent.putExtra(DetailActivity.EXTRA_POPULARITY, anime.popularity)
            detailIntent.putExtra(DetailActivity.EXTRA_MEMBERS, anime.members)
            detailIntent.putExtra(DetailActivity.EXTRA_SYNOPSIS, anime.synopsis)
            detailIntent.putExtra(DetailActivity.EXTRA_BACKGROUND, anime.background)
            detailIntent.putExtra(DetailActivity.EXTRA_YTLINK, anime.youtube_link)
            detailIntent.putExtra(DetailActivity.EXTRA_MLLINK, anime.ml_link)
            startActivity(detailIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
               val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)

            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun showRecyclerList(){
        rvAnimes.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnimes.adapter = listAnimeAdapter


        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                showSelectedAnime(data)
            }
        })

    }
}