package com.aaronyii.photogram

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aaronyii.photogram.models.Post
import com.bumptech.glide.Glide

class PostsAdapter (val context: Context, val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private lateinit var tvUsername: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {

            itemView.findViewById<TextView>(R.id.tvUsername).text = post.user?.username
            itemView.findViewById<TextView>(R.id.tvDescription).text = post.description
            Glide.with(context).load(post.imageUrl).into(itemView.findViewById<ImageView>(R.id.ivPost))
            itemView.findViewById<TextView>(R.id.tvRelativeTime).text = DateUtils.getRelativeTimeSpanString(post.creationTimeMs)
        }
    }

}