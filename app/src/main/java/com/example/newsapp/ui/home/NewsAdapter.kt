package com.example.newsapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.News
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var onLongClickListener: ((Int)-> Unit?)?= null
     var onClickListener: ((News)-> Unit?)?= null
    private var list = arrayListOf<News>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.GRAY)

        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)

        }
    }

    override fun getItemCount() = list.size

    fun addItem(news: News?) {
        news?.let {
            list.add(0, it)
            notifyItemInserted(list.indexOf(news))
        }
    }

    fun getItem(position: Int): News {
        return list[position]
    }

    fun getData(): ArrayList<News> {
        notifyDataSetChanged()
        return list
    }

    fun deeleteItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setList(list: ArrayList<News>) {
        this.list = list
        notifyDataSetChanged()

    }

    fun deleteItemsAndNotifyAdapter(pos: Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class NewsViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.tvTitle.text = news.title
            val simpleDateFormat = SimpleDateFormat("HH:mm, dd MMMM yyyy")
            val dataTime  = Date(news.createdAr)
            val time : String = simpleDateFormat.format(dataTime)
            binding.tvTitle.text = news.title
            binding.tvTime.text = time
            itemView.setOnClickListener{
                onClickListener?.invoke(news)
            }

            itemView.setOnLongClickListener {
                onLongClickListener?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }

        }

    }
}
