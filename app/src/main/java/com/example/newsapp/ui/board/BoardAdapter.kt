package com.example.newsapp.ui.board

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBoardBinding
import com.example.newsapp.databinding.ItemBoardBinding

class BoardAdapter(val context: Context, val navController: NavController) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {
 private val text = arrayListOf(
     "Читайте новости самым первым и будьте в курсе новых событий",
     "Добавляйте свою новость, чтобы проинформировать остальных",
     "Новость распространяется по серверу среди всех пользователей"
 )
    private val ImageView= arrayListOf(R.raw.lotti_news1,R.raw.lotti_news3,R.raw.lotti_news2)
    private val list = arrayListOf(
        "СВЕЖИЕ",
        "МОШНЫЕ",
        "НЕПОВТОРИМЫЕ"
    )

   inner class BoardViewHolder(private var binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(position: Int) {
           binding.tvTitle.text = list[position]
           binding.tvText.text = text[position]
           binding.imageView.setAnimation(ImageView[position])

       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
       holder.bind(position)
    }

    override fun getItemCount() = 3


}