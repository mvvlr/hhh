package com.example.newsapp.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemFriendsBinding

class FriendsAdapter(val context: Context, val navController: NavController) : RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {
    private val imageView = arrayListOf(
        R.color.purple_700,
        R.drawable.ava_one,
        R.color.gray,
        R.drawable.ava_tony_stark,
        R.color.teal_200,
        R.drawable.ava_volf,
        R.color.teal_700);


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            ItemFriendsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = 7


    inner class FriendViewHolder(private var binding: ItemFriendsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
binding.avatarka.setImageResource(imageView[position])
        }
    }

}