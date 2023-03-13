package com.example.minimlist

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class ListAnimeAdapter(private val listAnime : ArrayList<Anime>) : RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data : Anime)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val ivImgThumb : ImageView = itemView.findViewById(R.id.main_img_thumb)
        val tvScore : TextView = itemView.findViewById(R.id.main_score)
        val tvTitle : TextView = itemView.findViewById(R.id.main_title)
        val tvGenre : TextView = itemView.findViewById(R.id.main_genre)
        val tvSynopsis : TextView = itemView.findViewById(R.id.main_synopsis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.anime_item,parent, false)
        return  ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (image_thumb, image_land, title, genre, score, ranked, popularity, members, synopsis, background, yt_link, ml_link) = listAnime[position]
        holder.ivImgThumb.setImageResource(image_thumb)
        holder.tvScore.text = score
        holder.tvTitle.text = title
        holder.tvGenre.text = genre
        holder.tvSynopsis.text = synopsis

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAnime[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listAnime.size
}