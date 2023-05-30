package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsLayoutBinding

class NewsListAdapter(
    private var context: Context,
//    var itemList: ArrayList<News>,
    private val listener: NewsItemClicked
) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val itemList = ArrayList<News>()

    class ViewHolder(var binding: NewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = NewsLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = itemList[position].title
        holder.binding.authorName.text = "Author: ${itemList[position].author}"

        Glide.with(context).load(itemList[position].imageUrl).into(holder.binding.imageView);


        holder.binding.root.setOnClickListener() {
            listener.onItemClicked(itemList[position])
        }
    }

    fun updateNews(updatedNews: ArrayList<News>) {
        itemList.clear()
        itemList.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}